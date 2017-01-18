package projeto;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.pweb.carrinho.model.Carrinho;
import br.edu.ifpb.pweb.carrinho.model.Catalogo;
import br.edu.ifpb.pweb.carrinho.model.Item;
import br.edu.ifpb.pweb.carrinho.model.ItemCarrinho;


@WebServlet("/pedido")
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrinho carrinho;
		HttpSession sessao = request.getSession();
		Item item = Catalogo.getItem(request.getParameter("id"));
		
		if (!sessao.isNew() && sessao.getAttribute("carrinho") != null) {
			carrinho = (Carrinho) sessao.getAttribute("carrinho");
		} else {
			carrinho = new Carrinho();
			sessao.setAttribute("carrinho", carrinho);
		}
		
		carrinho.adicioneItem(item.getItemID());	
		List<ItemCarrinho> itens = carrinho.getItemsCarrinho();
		request.setAttribute("itens", itens);
		
		// Atualizando a quantidade de itens no carrinho
		if(request.getParameter("novaQtde")!= null) {
			int qtdeNova = Integer.parseInt(request.getParameter("novaQtde"));
			carrinho.setQtdeItens(item.getItemID(), qtdeNova);
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pedido/index.jsp");
		dispatcher.forward(request, response);
	}

}
