<%@ include file="header.jsp" %>
<script>
var layout = new sap.ui.layout.form.GridLayout();
var username = new sap.ui.commons.TextField({
	layoutData: new sap.ui.core.VariantLayoutData({
		multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 3}),
		                 	     new sap.ui.layout.form.GridElementData({hCells: "3"})]
	})
});
var password = new sap.ui.commons.PasswordField({
	layoutData: new sap.ui.core.VariantLayoutData({
		multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 3}),
		                 	     new sap.ui.layout.form.GridElementData({hCells: "3"})]
	})
});
var form = new sap.ui.layout.form.Form("F1", {
	title: new sap.ui.core.Title({text: "Login"}),
	layout: layout,
	formContainers: [
		new sap.ui.layout.form.FormContainer("F1C1",{
			formElements: [
				new sap.ui.layout.form.FormElement({
					label: "Username",
					fields: [username],
					layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4, margin: false})
				}),
				new sap.ui.layout.form.FormElement({
					label: "Password",
					fields: [password],
					layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4, margin: false})
				}),
                new sap.ui.layout.form.FormElement({
                        label: "",
                        fields: [new sap.ui.commons.Button({
                                    text: "Login",
					layoutData: new sap.ui.core.VariantLayoutData({
						multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 3}),
						                 	     new sap.ui.layout.form.GridElementData({hCells: "3"})]
					})
	                         }).attachPress(function(){
	                             $.post("/"+globals.oData.urlPrefix+"LoginAjax", {username: username.getValue(), password: password.getValue()}, function(msg) {
	                            	 var response = JSON.parse(msg);
	                            	 if(response.error === "") {
	                            		 location.href = "/"+globals.oData.urlPrefix;
	                            	 } else {
	                                     var responseDialog = new sap.ui.commons.Dialog({modal: true, autoClose: true});
	                                     var text = new sap.ui.commons.TextView("errorDialog", {text: response.error});
	                                     responseDialog.addContent(text);
	                                     responseDialog.attachClosed(function(){
	                                    	 responseDialog.destroy();
	                                     });
	                                     responseDialog.open();
	                            	 }
	                             })
	                         })
	                 ]
	         })
			]
		})
	]
});
form.placeAt("loginForm");
</script>
<div id="loginForm"></div>
<%@ include file="footer.jsp" %>