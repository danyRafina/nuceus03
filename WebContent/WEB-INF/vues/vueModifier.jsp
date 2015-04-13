<%@ page import="java.util.*"%>
<%@ page import="fr.noixcoop.nuceus.*"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" charset="utf-8">
<title>Insert title here</title>
<link type="text/css" href="style/css/bootstrap.css" rel="stylesheet">
<%
	String libelle = (String) request.getAttribute("libelle");
	boolean aoc = ((Boolean) request.getAttribute("aoc")).booleanValue();
%>
</head>
<body>
	<nav class="navbar navbar-header navbar-fixed-top navbar-inverse">
		<div class="navbar-brand black">Modifier une variété</div>
		<a class="navbar-right"
			style="color: white; font: 20px Arial bold; margin-top: 11px; margin-right: 23px !important"
			href="?action=visualiser">Retourner</a>
	</nav>

	<div class="container" style="margin-top: 50px">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h1>Modification de la variété</h1>
				<form class="form-group" method="GET">
					<ul class="list-group-item" style="list-style-type: none">
						<li> <h3> Libellé : <%=libelle%></h3></li>
						<li> <h3> AOC : <%if (aoc) {%> 
							<input type="checkbox" name="isAoc" checked="checked">
							<% } else { %> 
							<input type="checkbox" name="isAoc"> 
							
							<%}%>
							</h3>
							<input type="hidden" name="libelle" value="<%= libelle %>">
							<input type="hidden" name="action" value="modifier">
							
						</li>
						
						<li><button type="submit">Valider</button>
							<button type="button"
								onClick="location.href='?action=visualiser'">Annuler</button></li>
					</ul>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
