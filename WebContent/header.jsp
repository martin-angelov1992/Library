<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library</title>
<script id="sap-ui-bootstrap"
  src="https://sapui5.hana.ondemand.com/resources/sap-ui-core.js"
  data-sap-ui-theme="sap_goldreflection"
  data-sap-ui-libs="sap.ui.commons,sap.ui.table"
></script>
<script type="text/javascript">
var urlPrefix = "WebTest2/";
var globals = new sap.ui.model.json.JSONModel();
globals.loadData("/"+urlPrefix+"globals.json", {}, false); 
sap.ui.getCore().setModel(globals, "globals");
if(globals.oData.loggedIn) {
	var info = new sap.ui.commons.TextView({text: "You are logged in as "+globals.oData.username});
	var logout = new sap.ui.commons.Link({
        text: "(Logout)",
        href: "/"+urlPrefix+"Logout"
    }); 
	info.placeAt("header");
	logout.placeAt("header");
	var links = globals.oData.headerLinks;
	for(i in links) {
	    var link = new sap.ui.commons.Link({
	            text: i,
	            href: "/"+urlPrefix+links[i]
	        });
	    var separator = new sap.ui.commons.TextView({text: "|"});
	    separator.placeAt("header");
	    link.placeAt("header");
	}
	
} else {
    var link = new sap.ui.commons.Link({
        text: "Login",
        href: "/"+urlPrefix+"Login"
    });
    link.placeAt("header");
}
</script>
<style>
#successDialog {
	color: green;
}
#errorDialog {
	color: red;
}
</style>
</head>
<body> 
<div id="header"></div>