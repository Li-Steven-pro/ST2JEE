<%-- 
    Document   : details
    Created on : 22 nov. 2020, 10:10:13
    Author     : steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <h1>Interns details</h1>
            <form name="TableFormIndex" method="post" action="internController">

                <input type="hidden" name="id_student" value="${intern.id}" />
                <td><input type="text" name="GroupStudent" value=" ${intern.group}" />

                </td><!-- String -->
                <td><input type="text" name="LastNameStudent" value=" ${intern.last_name}"   />

                </td><!-- String -->
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
                <td><input type="text" name="NoteTech" value="${intern.mission.evalS.gradeTech}" /></td><!-- String -->
                <td><input type="text" name="NoteCom" value="${intern.mission.evalS.gradeCom}" /></td><!-- String -->
                <td><input type="submit" name="submit" value="Valid Edit"/></td>

            </form>
        </table>
    </body>
</html>
