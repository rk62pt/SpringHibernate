<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h4>2015</h4>
<hr/>
<div class="row">
<div class="col-md-8">
<!-- Blockquote Small -->
<p class="lead bg-info">
網站資訊
</p>
<ul class="lead bg-info">
<li>Spring MVC</li>
<li>Hibernate</li>
<li>JQuery</li>
<li>Bootstript</li>
</ul>



<span>By Ryan.</span>
</div>
<div class="col-md-4">
<p class="bg-primary">最新資訊（公佈欄）</p>
<table>
    <thead><tr><th>Code</th><th>Price</th><th>Time</th></tr></thead>
    <tbody id="price"></tbody>
  </table>

  <p class="new">
    Code: <input type="text" class="code"/>
    Price: <input type="text" class="price"/>
    <button class="add">Add</button>
    <button class="update">Update</button>
    <button class="remove-all">Remove All</button>
  </p>
</div>
</div>
<!--script src="https://cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script-->
<script src="jss/sockjs.js"></script>
<script src="jss/stomp.js"></script>
<script>
    //Create stomp client over sockJS protocol
    var socket = new SockJS("/SpringHibernate/ws");
    //var socket = new WebSocket("/SpringHibernate/ws");
    var stompClient = Stomp.over(socket);
    // Render price data from server into HTML, registered as callback
    // when subscribing to price topic
    function renderPrice(frame) {
      var prices = JSON.parse(frame.body);
      $('#price').empty();
      for(var i in prices) {
        var price = prices[i];
        $('#price').append(
          $('<tr>').append(
            $('<td>').html(price.code),
            $('<td>').html(price.price.toFixed(2)),
            $('<td>').html(price.timeStr)
          )
        );
      }
    }
    
    // Callback function to be called when stomp client is connected to server
    var connectCallback = function() {
      stompClient.subscribe('/topic/price', renderPrice);
    }; 
    // Callback function to be called when stomp client could not connect to server
    var errorCallback = function(error) {
      alert(error.headers.message);
    };
    // Connect to server via websocket
    stompClient.connect("guest", "guest", connectCallback, errorCallback);
    
    // Register handler for add button
    $(document).ready(function() {
      $('.add').click(function(e){
        e.preventDefault();
        var code = $('.new .code').val();
        var price = Number($('.new .price').val());
        var jsonstr = JSON.stringify({ 'code': code, 'price': price });
        stompClient.send("/app/addStock", {}, jsonstr);
        return false;
      });
      
      $('.update').click(function(e){
          e.preventDefault();
          stompClient.send("/app/updateStock");
          return false;
        });
    });
    
    // Register handler for remove all button
    $(document).ready(function() {
      $('.remove-all').click(function(e) {
        e.preventDefault();
        stompClient.send("/app/removeAllStocks");
        return false;
      });
    });
  </script>