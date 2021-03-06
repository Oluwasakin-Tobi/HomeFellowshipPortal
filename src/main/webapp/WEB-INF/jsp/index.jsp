<!doctype html>
<head>
 <script src="/assets/anyChart/anychart-base.min.js"></script> 
 <script src="/assets/anyChart/anychart-exports.min.js"></script>
 <script src="/assets/anyChart/anychart-jquery.min.js"></script>
 <script src="/assets/anyChart/anychart-ui.min.css"></script>
    <!-- <script src="https://cdn.anychart.com/releases/8.7.1/js/anychart-base.min.js" type="text/javascript"></script> -->
    <script>
anychart.onDocumentReady(function () {

    // set stage
    stage = anychart.graphics.create("container");

    // set layers

    //for the Winter Sales chart
    layer_1 = stage.layer();
    layer_1.zIndex(80);
    //for the Spring Sales chart
    layer_2 = stage.layer();
    //for the Summer Sales chart
    layer_3 = stage.layer();
    //for the Fall Sales chart
    layer_4 = stage.layer();
    //for the buttons
    layer_5 = stage.layer();
    layer_5.zIndex(100);

    // set data for the Winter Sales
    var data_1 = [
      {x: "Ice-Cream", value: (${chartF}[0]), fill:"#00FFFF"},
      {x: "Sweets", value: 10000, fill:"#0DD9E6"},
      {x: "Chocolates", value: 19000, fill:"#1AB2CC"},
      {x: "Hot chocolate", value: 16000, fill:"#268CB2"},
      {x: "Cookies", value: 9000, fill:"#336699"} 
    ];

    // chart type
    var chart_1 = anychart.column();
    chart_1.title("Winter Sales");
    chart_1.padding(50, 0, 0, 0);
    var series_1 = chart_1.column(data_1);
    series_1.stroke(null);

    // draw
    chart_1.container(layer_1).draw();

    // set data for the Spring Sales
    var data_2 = [
      {x: "Ice-Cream", value: 10000, fill:"#FFCC99"},
      {x: "Sweets", value: 12000, fill:"#F2BFB2"},
      {x: "Chocolates", value: 13000, fill:"#E6B2CC"},
      {x: "Hot chocolate", value: 6000, fill:"#D9A6E6"},
      {x: "Cookies", value: 3000, fill:"#CC99FF"}
    ];

    // set series data
    var chart_2 = anychart.column();
    chart_2.title("Spring Sales");
    chart_2.padding(50, 0, 0, 0);
    var series_2 = chart_2.column(data_2);
    series_2.stroke(null);

    // draw
    chart_2.container(layer_2).draw();

    //set data for the Summer Sales
    var data_3 = [
      {x: "Ice-Cream", value: 16350, fill:"#CCFF33"},
      {x: "Sweets", value: 8930, fill:"#99EF50"},
      {x: "Chocolates", value: 3400, fill:"#66DF6C"},
      {x: "Hot chocolate", value: 2780, fill:"#33CF88"},
      {x: "Cookies", value: 2000, fill:"#00bfa5"}
    ];

    // set series data
    var chart_3 = anychart.column();
    chart_3.title("Summer Sales");
    chart_3.padding(50, 0, 0, 0);
    var series_3 = chart_3.column(data_3);
    series_3.stroke(null);

    // draw
    chart_3.container(layer_3);
    chart_3.draw();

    var data_4 = [
      {x: "Ice-Cream", value: 5000, fill:"#FFA000"},
      {x: "Sweets", value: 7000, fill:"#DF7800"},
      {x: "Chocolates", value: 8440, fill:"#C05000"},
      {x: "Hot chocolate", value: 9800, fill:"#A02800"},
      {x: "Cookies", value: 10250, fill:"#800000"}
    ];


    // set series data
    var chart_4 = anychart.column();
    chart_4.title("Fall Sales");
    chart_4.padding(50, 0, 0, 0);
    var series_4 = chart_4.column(data_4);
    series_4.stroke(null);

    // draw
    chart_4.container(layer_4).draw();
});

function winter() {
  stage.suspend();
  layer_1.zIndex(1000000);
  layer_2.zIndex(0);
  layer_3.zIndex(0);
  layer_4.zIndex(0);
  stage.resume();
};

function spring() {
  stage.suspend();
  layer_1.zIndex(0);
  layer_2.zIndex(1000000);
  layer_3.zIndex(0);
  layer_4.zIndex(0);
  stage.resume();
};

function summer() {
  stage.suspend();
  layer_1.zIndex(0);
  layer_2.zIndex(0);
  layer_3.zIndex(1000000);
  layer_4.zIndex(0);
  stage.resume();
};

function autumn() {
  stage.suspend();
  layer_1.zIndex(0);
  layer_2.zIndex(0);
  layer_3.zIndex(0);
  layer_4.zIndex(1000000);
  stage.resume();
};
</script>
</head>
<body>
<button onclick="winter()">Winter Sales</button>
<button onclick="spring()">Spring Sales</button>
<button onclick="summer()">Summer Sales</button>
<button onclick="autumn()">Autumn Sales</button>
    <div id="container" style="width: 500px; height: 400px;"></div>
</body>
</html>
