<%@ include file="header.jsp" %>
<script type="text/javascript">
var columns = {name: "Name", author: "Author", description: "Description"/*, amount: "Amount", booking: "Booking", edit: "Edit", delete: "Delete"*/};
var model = new sap.ui.model.json.JSONModel();
var table = new sap.ui.table.Table({
    title: "books",
    visibleRowCount: 7,
    selectionMode: sap.ui.table.SelectionMode.Single
});
for(i in columns) {
    var column = new sap.ui.table.Column({
        label: new sap.ui.commons.Label({text: columns[i]}),
        template: new sap.ui.commons.TextView().bindProperty("text", i),
        sortProperty: i,
        filterProperty: i,
        width: "200px"
    });
    table.addColumn(column);
}
table.addColumn(new sap.ui.table.Column({
        label: new sap.ui.commons.Label({text: "Amount"}),
        template: new sap.ui.commons.TextView().bindProperty("text", {
            parts: [
                {path: "availableCount", type: new sap.ui.model.type.String()},
                {path: "totalCount", type: new sap.ui.model.type.String()}
            ],
            formatter: function(availableCount, totalCount){
              return availableCount + "/" + totalCount;
            }
        }),
        sortProperty: "amount",
        filterProperty: "amount",
        width: "200px"
}));
model.loadData("/"+urlPrefix+"books/books.json");
table.setModel(model);
table.bindRows("/model");
table.placeAt("books");
</script>
<div id="books"></div>
<%@ include file="footer.jsp" %>