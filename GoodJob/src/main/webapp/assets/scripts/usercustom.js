$(document).ready(function(){
  $(".report-btn").click(function(){
    var boardType = $(this).data("boardType");
    var seq = $(this).data("seq");
    $("#board-type").val(boardType);
    $("#seq-input").val(seq);
    $("#modal").show();
  });

  $("#cancel-btn").click(function(){
    $("#modal").hide();
  });

  $(window).click(function(event){
    if(event.target == $("#modal")[0]){
      $("#modal").hide();
    }
  });
});