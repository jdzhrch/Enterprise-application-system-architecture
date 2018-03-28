<%@ page import="java.util.ArrayList"%>
﻿<%@ page import="java.util.List"%>
﻿<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.Book"%>
<%@ page import="model.User"%>
<%@ page import="model.Order"%>
<%@ page import="model.Orderitem"%>
<%@ page import="cipher.AESCipher"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BookStore</title>

<%
String path = request.getContextPath();
%>
<link href="<%=path%>/bookstore/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/flexslider.css" rel="stylesheet" >
<link href="<%=path%>/bookstore/css/styles.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/queries.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/animate.css" rel="stylesheet">


  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
	<%
		User account = new User();
		account = (User) session.getAttribute("account");

		List<Order> orders = (List<Order>) request.getAttribute("orders");
		List<List<Orderitem>> orderitemses = (List<List<Orderitem>>) request.getAttribute("orderitemses");
		List<List<Book>> bookses = (List<List<Book>>) request.getAttribute("bookses");
		
		int cart_index = -1;
		Order cart_order = new Order();
		List<Orderitem> cart_orderitems = new ArrayList<Orderitem>();
		List<Book> cart_books = new ArrayList<Book>();
		if(request.getAttribute("cart_index") != null){
			cart_index = (int) request.getAttribute("cart_index");
			cart_order = orders.get(cart_index);
			cart_orderitems = orderitemses.get(cart_index);
			cart_books = bookses.get(cart_index);
		}
		String cart_orderid =AESCipher.AESEncode(Integer.toString(cart_order.getId()));
		String cart_orderuserid = AESCipher.AESEncode(Integer.toString(cart_order.getUserid()));
	%>
<body id="top">
  <header id="home">
    <nav>
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-8 col-xs-offset-2">
            
              <ul>
                <li><a href="HomepagePro">Home <span class="indicator"><i class="fa fa-angle-right"></i></span></a></li>
                <%
                	if(account != null){
                		if(account.getRole().equals("admin")){
                %>
                			<li><a href="allUserPro">Backstage <span class="indicator"><i class="fa fa-angle-right"></i></span></a></li>
               <%
                		}
                	}
               %>
              </ul>
          </div>
        </div>
      </div>
    </nav>
    <section class="hero" id="hero">
      <div class="container">
        <div class="row" >
        <%
        	if(account == null){
        		response.sendRedirect("HomepagePro");
        	}
        	else{
        %>
        	<div style="width:300px; height:auto; float:left; display:inline">
        	Username :
        	<a href="#"><%=account.getUsername()%></a>
        	&nbsp;&nbsp;&nbsp;&nbsp;Role : 
            <a href="#"><%=account.getRole()%></a>
            <br />
            <a href="logoutUserPro" style=" color:#666; font-size:20px;">Logout</a>
        	
        	</div>
        <%
        	}
        %>
          <div class="col-md-12 text-right navicon" style="width:300px; height:auto; float:right; display:inline">
            <a id="nav-toggle" class="nav_slide_button" href="#"><span></span></a>
          </div>
        </div>
        </div>
    </section>
  </header>
   
  <!-- cart -->
  <%
  	if(request.getAttribute("cart_index") != null && cart_orderitems.size()>0){
	%>
  <section class="hero features text-center section-padding" >
    <div class="container">
      <div class="row">
        <div class="col-md-12">
        <%
        int cart_total = 0;
        for(int i=0;i<cart_orderitems.size();i++){
        	cart_total +=cart_orderitems.get(i).getOrderitemPrice();
        }
        %>
          <h1 class="arrow">Here is your cart &nbsp;total : ¥<%=cart_total/100.0 %></h1>
          
  <!-- /#wrapper details-->

	<div class="modal fade" id="modal-order" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 id="details_tt"class="modal-title">Submit Order</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
				     		<form id="cart_deal_form" role="form" action="dealOrderPro" method="post">
								<div class="form-group" hidden>
									<input class="form-control" name="cart_orderid" value=<%=cart_orderid %>>
								</div>
								<div class="form-group"  hidden>
									<input class="form-control" name="cart_orderuserid" value=<%=cart_orderuserid %>>
								</div>
								<%
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
									String date = df.format(new Date());
								%>
								<div class="form-group"  hidden>
									<input id="cart_orderdate"class="form-control" name="date" value=<%=date %>>
								</div>
								<div class="form-group" >
									<label >Address</label>
									<input id="cart_orderaddress"class="form-control" name="address" >
								</div>
								<div class="form-group">
									<label >Phone</label>
									<input id="cart_orderphone"class="form-control" name="phone" >
								</div>
							</form>  
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="deal()">submit</button>
				</div>
			</div>
		</div>
	</div> 
	
          <h3><Button class="btn btn-default" onclick="javascript:$('#modal-order').modal('show');">Deal </Button></h3>
          
          <div class="features-wrapper">
           <%
				for (int i = 0; i < cart_books.size(); i++) {
													Book book = cart_books.get(i);
			%>
			    
				<form id="book_deal_form" role="form" action="dealBookPro" method="post" hidden>
					<div class="form-group">
						<input id="book_id" class="form-control" name="id" value=<%=book.getId() %>>
					</div>
					<div class="form-group" >
						<input id="book_stock" class="form-control" name="stock" value=<%=book.getStock() - cart_orderitems.get(i).getAmount() %>>
					</div>
				</form>   
            <div class="col-md-4  delay-04s">
              <div>
                <img src=<%="getPictureBookPro?title="+book.getTitle() %> width=200px/>
              </div>
              <h2><%=book.getTitle()%></h2>
              <p>by <%=book.getAuthor()%></p>
              <p>¥  <%=cart_orderitems.get(i).getOrderitemPrice()/100.0/cart_orderitems.get(i).getAmount() %></p>
			  <p>Amount: <%=cart_orderitems.get(i).getAmount()%></p> 
			  <Button class="btn btn-default" onclick=<%="remove_from_cart("+cart_orderitems.get(i).getId()+")" %>>delete from cart </Button> 
            </div>
            
			<%
				}
			%>
            <div class="clearfix"></div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <%
  	}
  	else{
  %>
  <section class="hero features text-center section-padding" >
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="arrow">Your cart is empty</h1>
        </div>
      </div>
    </div>
  </section>
  <%
  	}
  %>
  
  <!--history orders-->
  <%
  	if(orders != null){
  		if((orders.size() == 1 && request.getAttribute("cart_index") == null)
  				||(orders.size()>1)){
  %>
        <div class="col-md-12" align="center">
          <h1 class="arrow">Here are your history orders</h1>
        </div>
  
  <%
  			for(int j=orders.size()-1;j>=0;j--){
  				if(orders.get(j).getDate() != null){
  					List<Orderitem> history_orderitems = orderitemses.get(j);
  					List<Book> history_books = bookses.get(j);
	%>
  <section class="hero features text-center section-padding" >
    <div class="container">
      <div class="row">
        <div class="col-md-12">
        <%
        int history_total = 0;
        for(int i=0;i<history_orderitems.size();i++){
        	history_total +=history_orderitems.get(i).getOrderitemPrice();
        }
		%>
          <h1 class="arrow"><%=orders.get(j).getDate() %> &nbsp;total : ¥<%=history_total/100.0 %>
          	&nbsp;<%=orders.get(j).getEnddate()==null? "": "Receipt Date:"+orders.get(j).getEnddate()%></h1>
        <%
          if(orders.get(j).getEnddate()==null){
        %>
        	<h3><Button id=<%="button_confirm"+j%> class="btn btn-default" 
        		onclick=<%="confirm_receipt("+orders.get(j).getId()+","+j+")" %>>Confirm receipt </Button></h3>
        <%} %>
          <div class="features-wrapper">
           <%
				for (int i = 0; i < history_books.size(); i++) {
													Book book = history_books.get(i);
			%>
			    
				
            <div class="col-md-4  delay-04s">
              <div>
                <img src=<%="getPictureBookPro?title="+book.getTitle() %> width=200px/>
              </div>
              <h2><%=book.getTitle()%></h2>
              <p>by <%=book.getAuthor()%></p>
              <p>¥  <%=history_orderitems.get(i).getOrderitemPrice()/100.0/history_orderitems.get(i).getAmount() %></p>
			  <p>Amount: <%=history_orderitems.get(i).getAmount()%></p>       
            </div>
            
			<%
				}
			%>
            <div class="clearfix"></div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <%
  				}
  			}
  		}
  	}
  	else{
  %>
  <section class="hero features text-center section-padding" >
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="arrow">You don't have history orders</h1>
        </div>
      </div>
    </div>
  </section>
  <%
  	}
  %>
  
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="<%=path%>/bookstore/js/waypoints.min.js"></script>
  <script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
  <script src="<%=path%>/bookstore/js/scripts.js"></script>
  <script src="<%=path%>/bookstore/js/jquery.flexslider.js"></script>
  <script src="<%=path%>/bookstore/js/modernizr.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>
	<script src="<%=path%>/bookstore/js/cart.js"></script>
<script>
function remove_from_cart(orderitemid){
	bootbox.confirm({
		buttons : {
			confirm : {
				label : 'Delete'
			},
			cancel : {
				label : 'Cancel'
			}
		},
		message : 'Sure to delete?',
		callback : function(result) {
			if (result) {
				var id = orderitemid;
				jQuery.ajax({
					url : 'deleteOrderitemPro',
					processData : true,
					dataType : "text",
					data : {
						id : id
					},
					success : function(data) {
						console.log(id);
						bootbox.alert({
							message : 'Delete Successfully! ',
						    callback : function() {
								location.reload();
							}
						});
					}
				});

			}
		}
	});
}
</script>
</body>
</html>