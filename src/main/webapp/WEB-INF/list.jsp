<%-- 
    Document   : list
    Created on : 22 nov. 2020, 10:09:21
    Author     : steve
--%>
<%-- 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List of interns</h1>
        <table>
            <c:forEach items="${internsList}" var="intern">
                <tr>
                    <td>${intern.last_name}</td>
                </tr>

            </c:forEach>
        </table>
    </body>
</html>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: etienne
  Date: 12/11/2020
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>

    <form name="Recherche" method="post" action="TableSearch">
        <td><input type="text" name="search" /></td>
        <td><input type="submit" name="search" value="Search"/></td>
    </form>
    <form name="LogOut" method="post" action="Logout">
        <td><input type="submit" name="detail" value="Log Out"/></td>
    </form>
    <table>
        <tr>
            <td>Group</td>
            <td>Last Name</td>
            <td>Fiche visite</td>
            <td>Fiche eval</td>
            <td>Rapport</td>
            <td>Soutenance</td>
            <td>Plannif</td>
            <td>Faite</td>
            <td>Stage Date debut</td>
            <td>Stage Date fin</td>
            <td>Entreprise nom</td>
            <td>Maitre de stage</td>
            <td>Adresse</td>
            <td>Note technique</td>
            <td>Notet com</td>
        </tr>
        #<c:forEach items="${internsList}" var="intern">


            <tr>
            <form name="TableFormIndex" method="get" action="ControllerDB">

                <input type="hidden" name="id_student" value="" />
                <td><input type="text" name="GroupStudent" value=" ${intern.group}" />

                </td><!-- String -->
                <td><input type="text" name="LastNameStudent" value=" ${intern.last_name}"   />

                </td><!-- String -->
                <td>
                    <input type="checkbox" id="cdc" name="cdc"
                           ${intern.last_name}
                           </td>
                    <!-- String -->
                <td>
                    <input type="checkbox" id="fiche_visite" name="fiche_visite"
                           ${intern.mission.visitS!=null?'checked':''}/>


                </td><!-- String -->
                <td>
                    <input type="checkbox" id="fiche_eval" name="fiche_eval"
                           ${intern.mission.evalS!=null?'checked':''}/>

                </td><!-- String -->


                <td>
                    <input type="checkbox" id="soutenance" name="soutenance" 
                           ${intern.mission.soutenance!=null?'checked':''}/>


                </td><!-- String -->
                <td>
                    <input type="checkbox" id="plannif" name="plannif" 
                           ${intern.mission.visitS.planned!=null?'checked':''}/>

                </td><!-- String -->
                <td>
                    <input type="checkbox" id="faite" name="faite"
                           ${intern.mission.visitS.done!=null?'checked':''}/>

                </td><!-- String -->
                <td><input type="text" name="Debut" value="${intern.mission.startDate}" /></td><!-- String -->
                <td><input type="text" name="Fin" value="${intern.mission.endDate}" /></td><!-- String -->
                <td><input type="text" name="NomEntreprise" value="" /></td><!-- String -->
                <td><input type="text" name="Mds" value="" /></td><!-- String -->
                <td><input type="text" name="Adresse" value="${intern.address}" /></td><!-- String -->
                    <%--<td><input type="text" name="NoteTech" value="${intern.mission.evalS.gradTech}" /></td><!-- String -->
                    <td><input type="text" name="NoteCom" value="${intern.mission.evalS.gradCom}" /></td><!-- String -->
                    <td><input type="submit" name="submit" value="Valid Edit"/></td>
                    --%>
            </form>
            <form name="Actual_intern" method="post" action="ControllerDB">
                <input type="hidden" name="Intern" value="i" />
                <td><input type="submit" name="detail" value="Detail"/></td>
            </form>
        </tr>
        </br>
    </c:forEach>
    <h2>FIN, CE PROFESSEUR POSSEDE ${fn:length(internsList)} ETUDIANTS</h2>


</table>

</body>
