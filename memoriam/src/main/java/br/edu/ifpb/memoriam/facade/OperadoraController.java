package br.edu.ifpb.memoriam.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.memoriam.dao.OperadoraDAO;
import br.edu.ifpb.memoriam.dao.PersistenceUtil;
import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Operadora;

public class OperadoraController {
	
	private Operadora operadora;
	private List<String> mensagensErro;

	public List<Operadora> consultar() {
		OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.findAll();
	}
	
	public Operadora buscar(int id) {
		OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
		Operadora operadora = dao.find(id);
		return operadora;
	}
	
	public Resultado cadastrar(Map<String, String[]> parametros) {
		OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = new Resultado();

		if (isParametrosValidos(parametros)){
			dao.beginTransaction();
			if (this.operadora.getId() == null) {
				dao.insert(this.operadora);
			} else {
				dao.update(this.operadora);
			}
			dao.commit();
			resultado.setErro(false);
			resultado.setMensagensErro(Collections.singletonList("Operadora criada com sucesso."));
		} else {
			resultado.setEntitade(this.operadora);
			resultado.setErro(true);
			resultado.setMensagensErro(this.mensagensErro);
		}
		
		return resultado;
	}
	
	private boolean isParametrosValidos(Map<String, String[]> parametros) {
		String[] id = parametros.get("id");
		String[] nome = parametros.get("nome");
		String[] prefixo = parametros.get("prefixo");

		this.operadora = new Operadora();
		this.mensagensErro = new ArrayList<String>();
		
		// Verifica ID
		if (id != null && id.length > 0 && !id[0].isEmpty()) {
			operadora.setId(Integer.parseInt(id[0]));
		}
		
		// Verifica nome
		if (nome == null || nome.length == 0 || nome[0].isEmpty()) {
			this.mensagensErro.add("Nome é campo obrigatório!");
		} else {
			operadora.setNome(nome[0]);
		}

		// Verifica prefixo
		if(prefixo == null|| prefixo.length == 0 || prefixo[0].isEmpty()) {
			this.mensagensErro.add("Fone é campo obrigatório!");
		} else {
			operadora.setPrefixo(Integer.parseInt(prefixo[0]));
		}
		
		return this.mensagensErro.isEmpty();
	}
}
