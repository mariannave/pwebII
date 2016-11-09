package br.edu.ifpb.memoriam.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.memoriam.dao.ContatoDAO;
import br.edu.ifpb.memoriam.dao.OperadoraDAO;
import br.edu.ifpb.memoriam.dao.PersistenceUtil;
import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Operadora;

public class ContatoController {
	private Contato contato;
	private List<String> mensagensErro;
	
	public List<Contato> consultar() {
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		List<Contato> contatos = dao.findAll();
		return contatos;
	}
	
	public Contato buscar(int id) {
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		Contato contato = dao.find(id);
		return contato;
	}
	
	public Resultado deletar(String[] contatosIds) {
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = new Resultado();
		this.mensagensErro = new ArrayList<String>();

		for (String contatoId : contatosIds) {
			Contato contato = dao.find(Integer.parseInt(contatoId));
			
			if (contato == null) {
				System.out.println("ERRO no ID: " + contatoId);
				this.mensagensErro.add("Erro ao deletar contato de ID: " + contatoId);
			} else {
				dao.beginTransaction();
				dao.delete(contato);
				dao.commit();
			}
		}

		if (this.mensagensErro.isEmpty()) {
			resultado.setErro(false);
			resultado.setMensagensErro(Collections.singletonList("Deleção feita com sucesso"));
		} else {
			resultado.setErro(true);
			resultado.setMensagensErro(this.mensagensErro);
		}

		return resultado;
		
	}

	public Resultado cadastrar(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();
		
		if (isParametrosValidos(parametros)) {
			ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
			dao.beginTransaction();
			
			if (this.contato.getId() == null) {
				dao.insert(this.contato);
			} else {
				dao.update(this.contato);
			}

			dao.commit();
			resultado.setErro(false);
			resultado.setMensagensErro(Collections.singletonList("Contato criado com sucesso."));
		} else {
			resultado.setEntitade(this.contato);
			resultado.setErro(true);
			resultado.setMensagensErro(this.mensagensErro);
		};

		return resultado;		
	}
	
	private boolean isParametrosValidos(Map<String, String[]> parametros) {
		String[] id = parametros.get("id");
		String[] nome = parametros.get("nome");
		String[] fone = parametros.get("fone");
		String[] dataAniv = parametros.get("dataAniv");
		Operadora operadora = null;
		String idOperadora = parametros.get("operadora")[0];

		this.contato = new Contato();
		this.mensagensErro = new ArrayList<String>();
		
		// Verifica ID
		if (id != null && id.length > 0 && !id[0].isEmpty()) {
			contato.setId(Integer.parseInt(id[0]));
		}

		// Adiciona operadora
		if (idOperadora != null && !idOperadora.isEmpty()) {
			OperadoraDAO opDao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
			operadora = opDao.find(Integer.parseInt(idOperadora));
		}
		// Verifica operadora
		if (operadora == null) {
			this.mensagensErro.add("É necessário selecionar uma operadora.");
		} else {
			contato.setOperadora(operadora);
		}
		
		// Verifica nome
		if (nome == null || nome.length == 0 || nome[0].isEmpty()) {
			this.mensagensErro.add("Nome é campo obrigatório!");
		} else {
			contato.setNome(nome[0]);
		}

		// Verifica telefone
		if(fone == null|| fone.length == 0 || fone[0].isEmpty()) {
			this.mensagensErro.add("Fone é campo obrigatório!");
		} else {
			contato.setFone(fone[0]);
		}
		
		if (dataAniv == null || dataAniv.length == 0 || dataAniv[0].isEmpty()) {
			this.mensagensErro.add("Data de aniversário é campo obrigatório!");
		} else {
			if(dataAniv[0].matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2,2}")) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					sdf.setLenient(false);
					Date dataIni = sdf.parse(dataAniv[0]);
					contato.setDataAniversario(dataIni);
				} catch(ParseException e) {
					this.mensagensErro.add("Data inválida para a data de aniversário!");
				}
			} else {
				this.mensagensErro.add("Formato inválido para a data de aniversário (use dd/mm/aaaa)!");
			}
		}

		return this.mensagensErro.isEmpty();
	}
	
}
