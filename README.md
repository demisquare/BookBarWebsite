# BookBarWebsite

---
# Alcuni snippet untili
### La servlet serve un file jsp liscio e gli passa un parametro
```java
request.setAttribute("MyData", data);
RequestDispatcher rd = request.getRequestDispatcher("page.jsp");
rd.forward(request, response);
```
