<%-- 
    Document   : details
    Created on : 22 nov. 2020, 10:10:13
    Author     : steve
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
           prefix="fn" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>EFREI Paris - Details Intern</title>
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
            <c:set var="Teacher" value="User" />
            <h3><c:out value="${sessionScope[Teacher].last_name} ${sessionScope[Teacher].first_name}"/></h3>
            <br>
        </div>
        <div class="login-page">
            <div class="container-students the-containers" style="overflow-y:scroll">
                <div class="container">
                    <form name="GlobalForm" method="post" action="intern">
                        <input type="hidden" name="id_student" value="${intern.id}" />
                        <div class="row">
                            <div class="container">
                                <div class="card-deck" style=" padding:30px">
                                    <div class="card">
                                        <div class="card-header">
                                            Intern
                                        </div>
                                    <div class="card-body">
                                        <div class="card-text">
                                            <label>Last name :</label>
                                            <input type="text" name="LastNameStudent" value="${intern.last_name}" style="margin-left:5px;"/>
                                    </div>
                                        <div class="card-text">
                                            <label>First name :</label>
                                            <input type="text" name="FirstNameStudent" value="${intern.first_name}" size="5"/>
                                        </div>
                                        <div class="card-text">
                                            <label>Address :</label>
                                            <input type="text" name="AddressStudent" value="${intern.address}" size="5"/>
                                        </div>
                                        <div class="card-text">
                                            <label>Skills :</label>
                                            <textarea name="SkillsStudent"> ${intern.skills} </textarea>
                                        </div>
                                        <div class="card-text">
                                            <label>Student linkedin :</label>
                                            <input type="text" name="LinkedinStudent" value="${intern.linkedin}" size="5"/>
                                        </div>
                                        <div class="card-text">
                                            <label>Student group :</label>
                                            <input type="text" name="GroupStudent" value="${intern.group}" size="5"/>
                                        </div>
                                        <div class="card-text">
                                            <label>Birthday :</label>
                                            <input type="text" name="BirthdayStudent" value="${intern.birthday}" maxlength="10" size="10" placeholder="YYYY-MM-DD"/>
                                        </div>
                                    </div>
                                </div>
                                    <div class="card">
                                        <div class="card-header">
                                            Mission
                                        </div>
                                        <div class="card-body">
                                            <div class="card-text">
                                                <label>Report title :</label>
                                                <input type="text" name="Report_titleMission" value="${intern.mission.report_title}"/>
                                            </div>
                                            <div class="card-text">
                                                <label>Year :</label>
                                                <input type="text" name="YearMission" value="${intern.mission.year}" size="5"/>
                                            </div>
                                            <div class="card-text">
                                                <label>Starting day :</label>
                                                <input type="text" name="StartMission" value="${intern.mission.startDate}" maxlength="10" size="10" placeholder="YYYY-MM-DD"/>
                                            </div>
                                            <div class="card-text">
                                                <label>Ending day :</label>
                                                <input type="text" name="EndMission" value="${intern.mission.endDate}" maxlength="10" size="10" placeholder="YYYY-MM-DD"/>
                                            </div>
                                            <div class="card-text">
                                                <label>Comment of the intern :</label>
                                                <textarea name="CommentMission">${intern.mission.comment}</textarea>
                                            </div>
                                            <div class="card-text">
                                                <label>meeting information :</label>
                                                <textarea name="MeetingInfoMission">${intern.mission.meetingInfo}</textarea>
                                            </div>
                                            <div class="card-text">
                                                <label>Soutenance :</label>
                                                <input type="checkbox" id="soutenance" name="soutenanceMission" 
                                                       ${intern.mission.soutenance?'checked':''}/>
                                            </div>
                                            <div class="card-text">
                                                <label>Key words :</label>
                                                <textarea name="keyWordMission">${intern.mission.keyWord}"</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-deck" style=" padding:30px">
                                    <div class="card">
                                        <div class="card-header">
                                            Visit sheet
                                        </div>
                                        <div class="card-body">
                                            <div class="card-text">
                                                <label>Planned :</label>
                                                <input type="checkbox" id="plannif" name="plannif" 
                                                       ${intern.mission.visitS.planned?'checked':''}/>
                                            </div>
                                            <div class="card-text">
                                                <label>Done :</label>
                                                <input type="checkbox" id="faite" name="faite"
                                                       ${intern.mission.visitS.done?'checked':''}/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header">
                                            Evaluation sheet
                                        </div>
                                        <div class="card-body">
                                            <div class="card-text">
                                                <label>Done : </label>
                                                    <input type="checkbox" id="fait" name="DoneEvalS"
                                                           ${intern.mission.evalS.done?'checked':''}/>
                                            </div>
                                            <div class="card-text">
                                                <label>Tech grade :</label>
                                                <input type="text" name="NoteTech" value="${intern.mission.evalS.gradeTech}" maxlength="4" size="4"/>
                                            </div>
                                            <div class="card-text">
                                                <label>Com grade :</label>
                                                <input type="text" name="NoteCom" value="${intern.mission.evalS.gradeCom}" maxlength="4" size="4"/>
                                            </div>
                                            <div class="card-text">
                                                <label>Comment of the supervisor : </label>
                                                    <input type="text" name="CommentEvalS" value="${intern.mission.evalS.comment}" />
                                            </div>
                                        </div>    
                                    </div>   
                                </div>
                            </div>
                        </div>
                        <div class="row" style="padding-bottom: 50px">
                            <div class="col-sm-6">
                                <a href="<%=application.getContextPath()%>/interns" style="padding:-10px;" class="btn-submit_me" > Retour </a>
                            </div>
                            <div class="col-sm-6">
                                <input style="padding:-10px;" class="btn-submit_me" id= "button-submit" type="submit" name="UpdateAll" value="Update interns" />
                            </div>
                        </div>
                    </form>
                </div>
            </div>      
        </div>
    </body>