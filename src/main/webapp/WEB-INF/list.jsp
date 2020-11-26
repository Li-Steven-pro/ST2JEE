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
    <form name="LogOut" method="post" action="logout">
        <td><input type="submit" name="detail" value="Log Out"/></td>
    </form>
    <form name="GlobalForm" method="post" action="intern/update">
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
        <!-- form for a single intern-->
        <c:forEach items="${internsList}" var="intern">
            <tr>
                <input type="hidden" name="id_student" value="${intern.id}" />
                <td><input type="text" name="GroupStudent${intern.id}" value=" ${intern.group}" />

                </td><!-- String -->
                <td><input type="text" name="LastNameStudent${intern.id}" value=" ${intern.last_name}"   />

                </td><!-- String -->
                <td>
                    <input type="checkbox" id="fiche_visite" name="fiche_visite${intern.id}"
                           ${intern.mission.visitS!=null?'checked':''}/>
                </td><!-- String -->
                <td>
                    <input type="checkbox" id="fiche_eval" name="fiche_eval${intern.id}"
                           ${intern.mission.evalS!=null?'checked':''}/>

                </td><!-- String -->


                <td>
                    <input type="checkbox" id="soutenance" name="soutenance${intern.id}" 
                           ${intern.mission.soutenance!=null?'checked':''}/>


                </td><!-- String -->
                <td>
                    <input type="checkbox" id="plannif" name="plannif${intern.id}" 
                           ${intern.mission.visitS.planned!=null?'checked':''}/>

                </td><!-- String -->
                <td>
                    <input type="checkbox" id="faite" name="faite${intern.id}"
                           ${intern.mission.visitS.done!=null?'checked':''}/>

                </td><!-- String -->
                <td><input type="text" name="Debut${intern.id}" value="${intern.mission.startDate}" /></td><!-- String -->
                <td><input type="text" name="Fin${intern.id}" value="${intern.mission.endDate}" /></td><!-- String -->
                <td><input type="text" name="NomEntreprise${intern.id}" value="" /></td><!-- String -->
                <td><input type="text" name="Mds${intern.id}" value="" /></td><!-- String -->
                <td><input type="text" name="Adresse${intern.id}" value="${intern.address}" /></td><!-- String -->
                <td><input type="text" name="NoteTech${intern.id}" value="${intern.mission.evalS.gradeTech}" /></td><!-- String -->
                <td><input type="text" name="NoteCom${intern.id}" value="${intern.mission.evalS.gradeCom}" /></td><!-- String -->
                <td><a href="<%=application.getContextPath()%>/intern/${intern.id}"/> <button type="button">Details</button></a></td>
            </tr>
            </br>
        </c:forEach>
        <c:set var="Teacher" value="User" />
        <h2>FIN, <c:out value="${sessionScope[Teacher].last_name} ${sessionScope[Teacher].first_name}"/> POSSEDE ${fn:length(internsList)} ETUDIANTS</h2>
    </table>
    <input type="submit" name="UpdateAll" value="Update interns" />
</form>
</body>
