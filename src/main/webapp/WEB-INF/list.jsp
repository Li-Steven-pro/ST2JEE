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
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>EFREI Paris - Intern</title>
</head>
<body style="overflow:hidden">
    <div class="header_me">
        <img src="efrei-paris_110x40.png" alt="Efrei Paris" class="app-logo_me" />

        <form style="display: flex;padding:10px;" id= "searchbox" name="Recherche" method="post" action="TableSearch">
            <input type="text" name="search" />
            <input style="padding:-10px;" class="btn-submit_me" id= "button-submit" type="submit" name="search" value="Search"/>
        </form>
        <form style="display: flex;padding:10px;" name="LogOut" method="post" action="logout">
            <input style="padding:-10px;" class="btn-submit_me" id= "button-submit" type="submit" name="detail" value="Log Out"/>
        </form>
        <h3><c:out value="${sessionScope[User].last_name} ${sessionScope[User].first_name} ETUDIANTS: ${fn:length(internsList)}"></c:out></h3>
            <br>

        </div>
        <div class="login-page">
            <div class="container-students the-containers" style="overflow-y:scroll">
                <form name="GlobalForm" method="post" action="intern/update">
                    <div class="yeet"></div>
                    </br></br></br></br>
                    <table >
                        <tr style="background-color:#3978bb;position: -webkit-sticky;
                            position: sticky; color: whitesmoke;height: 100%;z-index:auto;">
                            <td style="height:40px;">Groupe</td>
                            <td>Nom</td>
                            <td>Fiche visite</td>
                            <td>Fiche eval</td>
                            <td>Soutenance</td>
                            <td>Plannif</td>
                            <td>Faite</td>
                            <td>Date debut</td>
                            <td>Date fin</td>
                            <td>Adresse</td>
                            <td>Note tech</td>
                            <td>Note comm</td>
                        </tr>
                        <!-- form for a single intern-->
                    <c:forEach items="${internsList}" var="intern">
                        <tr style="overflow-y:scroll">
                            <td><input type="hidden" name="id_student" value="${intern.id}" />
                                <input type="text" name="GroupStudent${intern.id}" value="${intern.group}" size="5"/></td><!-- String -->
                            <td><input type="text" name="LastNameStudent${intern.id}" value="${intern.last_name}" style="margin-left:5px;"/></td><!-- String -->
                            <td><input type="checkbox" id="fiche_visite" name="fiche_visite${intern.id}"
                                       ${intern.mission.visitS!=null?'checked disabled="disabled"':''}/></td><!-- String -->
                            <td><input type="checkbox" id="fiche_eval" name="fiche_eval${intern.id}"
                                       ${intern.mission.evalS!=null?'checked disabled="disabled"':''}/></td><!-- String -->
                            <td><input type="checkbox" id="soutenance" name="soutenance${intern.id}" 
                                       ${intern.mission.soutenance!=null?'checked':''}/></td><!-- String -->
                            <td><input type="checkbox" id="plannif" name="plannif${intern.id}" 
                                       ${intern.mission.visitS.planned!=null?'checked':''}/></td><!-- String -->
                            <td><input type="checkbox" id="faite" name="faite${intern.id}"
                                       ${intern.mission.visitS.done!=null?'checked':''}/></td><!-- String -->
                            <td><input type="text" name="Debut${intern.id}" value="${intern.mission.startDate}" maxlength="10" size="10"/></td><!-- String -->
                            <td><input type="text" name="Fin${intern.id}" value="${intern.mission.endDate}" maxlength="10" size="10"/></td><!-- String -->
    <!--                        <td><input type="text" name="NomEntreprise${intern.id}" value="" /></td> String 
                            <td><input type="text" name="Mds${intern.id}" value="" /></td> String -->
                            <td><input type="text" name="Adresse${intern.id}" value="${intern.address}" /></td><!-- String -->
                            <td><input type="text" name="NoteTech${intern.id}" value="${intern.mission.evalS.gradeTech}" maxlength="4" size="4"/></td><!-- String -->
                            <td><input type="text" name="NoteCom${intern.id}" value="${intern.mission.evalS.gradeCom}" maxlength="4" size="4"/></td><!-- String -->
                            <td><a href="<%=application.getContextPath()%>/intern/${intern.id}"/> <button style="padding:-10px;" class="btn-submit_me" id= "button-submit" type="button">Details</button></a></td>
                        </tr>
                    </c:forEach>
                    <c:set var="Teacher" value="User" />
                    <!---->
                </table>
                <input style="padding:-10px;" class="btn-submit_me" id= "button-submit" type="submit" name="UpdateAll" value="Update interns" />
            </form>
            </body>
