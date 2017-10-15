$().ready(function(){
  fillList();
  $("#GC").click(function(){
    var selectedValue = $("#Program").val();
    if(parseInt(selectedValue) === 0){
      $("#outputText").text("Välj ett program och försök igen.")
    }else{


    }
  });
  $("#JSON").click(function(){
    var selectedValue = $("#Program").val();
    if(parseInt(selectedValue) === 0){
      $("#outputText").text("Välj ett program och försök igen.")
    }else{
      $.ajax({
        url: "http://localhost:8080/schedule/" + parseInt(selectedValue)
      }).then(function(data){
        
      })
    }
  });
});

function fillList(){
  console.log(1);
  $.ajax({
    url: "http://localhost:8080/getAll"
}).then(function(data){
    console.log(2);
    var j = 1;
    for(i = 0; i < data.length; i++){
      var program = document.createElement("option");
      program.setAttribute("value", j);
      program.appendChild(document.createTextNode(data[i]));
      document.getElementById("Program").appendChild(program);
      j++;
    }
    console.log(3);

  });
};
