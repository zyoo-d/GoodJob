$(document).ready(function(){
	
	$("#report-btn").click(function(){
		var boardType = $(this).data("boardType");
		$("#report-modal").show();
		$("#board-type").val(boardType);
	});
	
	$("#cancel-btn").click(function(){
		$("report-modal").hide();
	});
	
	$(window).click(function(event){
		if(event.target.id === "report-modal"){
			$(report-modal).hide();
		}
	});
	
});