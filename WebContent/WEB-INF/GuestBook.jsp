<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GuestBook</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function(){
   $("table").on("click", ".delete", function(){
       var row = $(this).closest("tr");
       $.ajax({
           url: "AjaxDeleteEntry",
           data: {
               "id" : row.attr("data-entry-id")
           },
           success: function(){
               row.remove();
           }
       });
   });
   
   $("#add").click(function(){
      var name = $("#name").val();
      var message = $("#message").val();
      if( name != "" && message != "" )
          $.ajax({
              url: "AjaxAddEntry",
              data: {
                  "name": name,
                  "message": message
              },
              success: function(data){
                  var row = $("<tr data-entry-id='" + data + "'></tr>" );
                  row.append("<td class='recordName'>" + name + "</td>")
                     .append("<td class='recordMessage'>" + message + "</td>")
                     .append("<td><button class='delete'>Delete</button></td>");
                  $("#form").before(row);
                  $("#name").val("");
                  $("#message").val("");
              }
          });
    });
   
   var content; 
   
   $("table").on("dblclick", "td", function() {
     if ($(this).children("input").length == 0) {
       content = $(this).html();
       
       $(this).html("");
       
       var input = '<input type="text" value="' + content +'"/> '
       $(this).append(input)
     }
   });

   $("table").on("blur", "input", function () {
       if ($(this).parent().hasClass("recordName") || $(this).parent().hasClass("recordMessage")) {
         var name;
         var message;
         
         var row = $(this).closest("tr");
         var id = row.attr("data-entry-id");
         
         if($(this).parent().hasClass("recordName")) {
             name = $(this).val();
             message = $(this).parent().next().html();
         }  
         
         else if ($(this).parent().hasClass("recordMessage")) {
             message = $(this).val();
             name = $(this).parent().prev().html();
         }
    
         $.ajax({
                url: "AjaxEditEntry",
                data: {
                    "id": id,
                    "name": name,
                    "message": message
                },
                
                success: function(){
                }
         });
         
         $(this).parent().html($(this).val());
         $(this).remove();
       }
    }); 
   
   
});
</script>
</head>
<body>
<h2>GuestBook</h2>
<table border="1">
  <tr><th>Name</th><th>Message</th><th></th></tr>
  <c:forEach items="${entries}" var="entry">
  <tr data-entry-id="${entry.id}">
    <td class="recordName">${entry.name}</td>
    <td class="recordMessage">${entry.message}</td>
    <td><button class="delete">Delete</button></td>
  </tr>
  </c:forEach>
  <tr id="form">
    <td><input id="name" type="text" /></td>
    <td><input id="message" type="text" /></td>
    <td><button id="add">Add</button></td>
</table>
</body>
</html>