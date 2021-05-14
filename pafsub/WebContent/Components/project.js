/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "ProjectAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
  location.reload(true);
 onItemSaveComplete(response.responseText, status);

 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).data("itemid"));
  $("#Name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Description").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Language").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Devname").val($(this).closest("tr").find('td:eq(3)').text());
 $("#Price").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "ProjectAPI",
 type : "DELETE",
 data : "itemID=" + $(this).data("itemid"),
 dataType : "text",
 complete : function(response, status)
 {

  location.reload(true);
 onItemDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validateItemForm()
{
// Name
if ($("#Name").val().trim() == "")
 {
 return "Insert Project Name.";
 }

// Description
if ($("#Description").val().trim() == "")
 {
	
 return "Insert Project Description.";
 } 
// Language------------------------
if ($("#Language").val().trim() == "")
 {
 return "Insert Project Language.";
 }
// Devname------------------------
if ($("#Devname").val().trim() == "")
 {
 return "Insert Project Devname.";
 }

// PRICE--------------------------
if ($("#Price").val().trim() == "")
 {
 return "Insert Project Price.";
 }
// is numerical value
var tmpPrice = $("#Price").val().trim();
if (!$.isNumeric(tmpPrice))
 {
 return "Insert a numerical value for Project Price.";
 }
// convert to decimal price
 $("#Price").val(parseFloat(tmpPrice).toFixed(2));
return true;
}
function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidItemIDSave").val("");
 $("#formItem")[0].reset();
}

function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 