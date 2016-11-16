package projeto;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb.carrinho.model.Carrinho;
import br.edu.ifpb.pweb.carrinho.model.Catalogo;
import br.edu.ifpb.pweb.carrinho.model.Item;
import br.edu.ifpb.pweb.carrinho.model.ItemCarrinho;


@WebServlet("/pedido")
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Catalogo catalogo = new Catalogo();
		Item item = catalogo.getItem(request.getParameter("id"));
		Carrinho carrinho = new Carrinho();
		carrinho.adicioneItem(item.getItemID());
		
		List<ItemCarrinho> itens = carrinho.getItemsCarrinho();
		request.setAttribute("itens", itens);
		
		
		System.out.println(itens.get(0).getNumItens());

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pedido/index.jsp");
		dispatcher.forward(request, response);
	}

}
