<%@ include file="../header.jsp" %>
<script type="text/javascript">
var layout = new sap.ui.layout.form.GridLayout();
var fields = [["Name", "name"], ["Faculty Number", "fn"]];
var fieldElements = new Array();
var addToForm = new Array();
for(i in fields) {
    fieldElements[fields[i][1]] = new sap.ui.commons.TextField(fields[i][1], {layoutData: new sap.ui.core.VariantLayoutData({
                                                    multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 3}),
                                                                                 new sap.ui.layout.form.GridElementData({hCells: "3"})]
                                            }),
                                            name: fields[i][1]
                                    }),


    addToForm.push(new sap.ui.layout.form.FormElement({
                    label: fields[i][0],
                    fields: [fieldElements[fields[i][1]]],
                    layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4, margin: false})})
    )
}
addToForm.push(new sap.ui.layout.form.FormElement({
        label: "",
        fields: [new sap.ui.commons.Button({
                    text: "Add book",
                                layoutData: new sap.ui.core.VariantLayoutData({
                                        multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 3}),
                                                                     new sap.ui.layout.form.GridElementData({hCells: "3"})]
                                })
                }).attachPress(function(){
                    var vals = {};
                    for(i in fieldElements) {
                        vals[fieldElements[i].getName()] = fieldElements[i].getValue();
                    }
                    $.post("", vals, function(response) {
                        var response = JSON.parse(response);
                        var responseDialog = new sap.ui.commons.Dialog({modal: true, autoClose: true});
                        var text = new sap.ui.commons.TextView(response.type+"Dialog", {text: response.msg});
                        responseDialog.addContent(text);
                        responseDialog.attachClosed(function() {
                        	responseDialog.destroy();
                        });
                        responseDialog.open();
                    });
                })
        ]
}))
var form = new sap.ui.layout.form.Form("F1", {
    title: new sap.ui.core.Title({text: "Add User"}),
    layout: layout,
    formContainers: [
		new sap.ui.layout.form.FormContainer("F1C1",{
			formElements: addToForm}
                )
    ]
});
form.placeAt("addUserForm");
</script>
<div id="addUserForm"></div>
<%@ include file="../footer.jsp" %>