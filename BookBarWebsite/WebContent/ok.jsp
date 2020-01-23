
<!-- header bar -->
<%@include file="component/header.jsp"%>
<div class="ok-page">
<div class="ok-txt">
<h1>Thank you! Order received</h1>
<p>In a few seconds you will be redirected to your personal page, if the link does not work click on the menu above</p>
</div>
<div class="ok-img">
<i class="fas fa-check-circle ok-ico"></i>
</div>
</div>

<!-- footer bar -->
<%@include file="component/footer.jsp"%>

<script>
setTimeout(function() {
  window.location.href = "http://localhost:8080/BookBarWebsite/user";
}, 1500);
</script>
</body>

</html>