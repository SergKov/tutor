<%--
  Created by IntelliJ IDEA.
  User: koval
  Date: 14.01.2017
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<templates:page_template>

    <jsp:attribute name="header">
        <homePage.title/>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="well">
                <form action="/" method="POST" class="form-horizontal">
                    <input type="hidden" name="controller" value="customerSignIn">

                    <div class="form-group">
                        <label class="control-label col-xs-5" for="email">email:</label>

                        <div class="col-xs-3">
                            <input type="text" pattern="${emailRegex}" class="form-control" id="email" name="email"
                                   placeholder="enterEmail" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-5" for="psw">password:</label>

                        <div class="col-xs-3">
                            <input type="password" pattern="${passwordRegex}" class="form-control" id="psw"
                                   name="password" required>
                        </div>
                    </div>
                    <div class="col-xs-offset-7 col-xs-5">
                        <button type="submit" class="btn btn-info btn-md"></button>
                    </div>
                    <p class="text-center"><a href="/signUp"></a></p>
                    <p class="text-center"><a href="/forgotPassword"></a></p>
                </form>
            </div>
        </div>
    </jsp:body>

</templates:page_template>
