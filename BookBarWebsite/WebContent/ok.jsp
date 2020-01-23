
<!-- header bar -->
<%@include file="component/header.jsp"%>
<h1>ok!</h1>


<!-- footer bar -->
<%@include file="component/footer.jsp"%>

<script>

//Redirect dopo 5 secondi
setTimeout(function() {
  window.location.href = "http://localhost:8080/BookBarWebsite/user";
}, 2000);

</script>
</body>

</html>