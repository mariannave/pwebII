package br.edu.ifpb.memoriam.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.facade.ContatoController;
import br.edu.ifpb.memoriam.facade.Resultado;

@WebServlet("/controller.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontControllerServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContatoController contatoCtrl = new ContatoController();
		String proxPagina = null;
		
		this.getServletContext().removeAttribute("msgs");
		
		String operacao = request.getParameter("op");
		if (operacao == null) {
			this.getServletContext().setAttribute("msgs", "Operacao (op) não especificada na requisição");
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}
		
		switch (operacao) {
		case "conctt":
			List<Contato> contatos = contatoCtrl.consultar();
			request.setAttribute("contatos", contatos);
			proxPagina = "contato/consulta.jsp";
			break;
		
		case "edtctt":
			int contatoId = Integer.parseInt(request.getParameter("id"));
			
			if (contatoId >= 0) {
				Contato contato = contatoCtrl.buscar(contatoId);
				request.setAttribute("contato", contato);
				proxPagina = "contato/cadastro.jsp";
			} else {
				proxPagina = "contato/consulta.jsp";
			}
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(proxPagina);
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().removeAttribute("msgs");
		
		String operacao = request.getParameter("op");
		if(operacao == null) {
			this.getServletContext().setAttribute("msgs", new String[] {"Operação (op) não especificada na requisição!"});
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}
		
		ContatoController contatoCtrl = new ContatoController();
		
		Resultado resultado = null;
		String paginaSucesso = "controller.do?op=conctt";
		String paginaErro = "contato/cadastro.jsp";
		String proxPagina = null;
		
		switch (operacao) {

		case "cadctt":
			resultado = contatoCtrl.cadastrar(request.getParameterMap());
			if(!resultado.isErro()) {
				proxPagina = paginaSucesso;
				request.setAttribute("msgs", resultado.getMensagensErro());
			} else {
				request.setAttribute("contato", (Contato) resultado.getEntitade());
				request.setAttribute("msgs", resultado.getMensagensErro());
				proxPagina = paginaErro;
			}
			break;

		case "delctt":
			resultado = contatoCtrl.deletar(request.getParameterValues("delete"));
			
			if (!resultado.isErro()) {
				request.setAttribute("msgs", resultado.getMensagensErro());
			} else {
				request.setAttribute("msgs", resultado.getMensagensErro());
			}

			proxPagina = "controller.do?op=conctt";
			break;

		default:
			request.setAttribute("erro", "Operação não especificada no servlet");
//			proxPagina = "../erro/erro.jsp";
			proxPagina = "controller.do?op=conctt";
		}
		
		if(resultado != null && resultado.isErro()){
			RequestDispatcher dispatcher = request.getRequestDispatcher(proxPagina);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(proxPagina);
		}		
	}

}
