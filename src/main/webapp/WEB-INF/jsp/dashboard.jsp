<%@ include file="authheader.jsp"%>
        <!--  <div id="layoutSidenav_content">  -->
            <main>
                <div class="container-fluid">
                    <h1 class="mt-4">Dashboard</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item active">Dashboard</li>
                    </ol>
                    <div class="row">
                        <div class="col-xl-3 col-md-6">
                            <div class="card bg-primary text-white mb-4">
                                <div class="card-body">Primary Card</div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="#">View Details</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-md-6">
                            <div class="card bg-warning text-white mb-4">
                                <div class="card-body">Warning Card</div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="#">View Details</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-md-6">
                            <div class="card bg-success text-white mb-4">
                                <div class="card-body">Success Card</div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="#">View Details</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-md-6">
                            <div class="card bg-danger text-white mb-4">
                                <div class="card-body">Danger Card</div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="#">View Details</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button onclick="centre()">Centre</button>
<button onclick="member()">Member</button>
 <button onclick="offering()">Offering</button>
<!--<button onclick="autumn()">Autumn Sales</button> -->
    <div id="container" style="width: 1300px; height: 400px;"></div>
                    <!-- <div class="row">
                        <div class="col-xl-6">
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-chart-area mr-1"></i> Area Chart Example
                                </div>
                                
                            </div>
                        </div>
                        <div class="col-xl-6">
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-chart-bar mr-1"></i> Bar Chart Example
                                </div>
                                <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                            </div>
                        </div>
                    </div> -->
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table mr-1"></i> Centre Details
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Centre Name</th>
                                            <th>Centre Leader</th>
                                            <th>Area</th>
                                            <th>Zone</th>
                                            <th>District</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Centre Name</th>
                                            <th>Centre Leader</th>
                                            <th>Area</th>
                                            <th>Zone</th>
                                            <th>District</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <tr>
                                            <td>Love</td>
                                            <td>Pastor Paul</td>
                                            <td>Area 1</td>
                                            <td>Zone 2</td>
                                            <td>District 3</td>
                                        </tr>
                                        
                                        <tr>
                                            <td>Joy</td>
                                            <td>Pastor Tony</td>
                                            <td>Area 2</td>
                                            <td>Zone 1</td>
                                            <td>District 1</td>
                                        </tr>
                                        
                                        <tr>
                                            <td>Love</td>
                                            <td>Pastor Alex</td>
                                            <td>Area 3</td>
                                            <td>Zone 3</td>
                                            <td>District 3</td>
                                        </tr>
                                        
                                        
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        <!-- </div> -->
        <%@ include file="footer.jsp"%>
        
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
      {x: "First Quarter", value: ${centreQuarter1}, fill:"#00FFFF"},
      {x: "Second Quarter", value: ${centreQuarter2}, fill:"#0DD9E6"},
      {x: "Third Quarter", value: ${centreQuarter3}, fill:"#1AB2CC"},
      {x: "Fourth Quarter", value: ${centreQuarter4}, fill:"#268CB2"}
    ];

    // chart type
    var chart_1 = anychart.column();
    chart_1.title("Centre");
    chart_1.padding(50, 0, 0, 0);
    var series_1 = chart_1.column(data_1);
    series_1.stroke(null);

    // draw
    chart_1.container(layer_1).draw();

    // set data for the Spring Sales
    var data_2 = [
      {x: "First Quarter", value: ${memberQuarter1}, fill:"#FFCC99"},
      {x: "Second Quarter", value: ${memberQuarter2}, fill:"#F2BFB2"},
      {x: "Third Quarter", value: ${memberQuarter3}, fill:"#E6B2CC"},
      {x: "Fourth Quarter", value: ${memberQuarter4}, fill:"#D9A6E6"}
    ];

    // set series data
    var chart_2 = anychart.column();
    chart_2.title("Member");
    chart_2.padding(50, 0, 0, 0);
    var series_2 = chart_2.column(data_2);
    series_2.stroke(null);

    // draw
    chart_2.container(layer_2).draw();

    //set data for the Summer Sales
    var data_3 = [
      {x: "First Quarter", value: ${offeringQuarter1}, fill:"#CCFF33"},
      {x: "Second Quarter", value: ${offeringQuarter2}, fill:"#99EF50"},
      {x: "Third Quarter", value: ${offeringQuarter3}, fill:"#66DF6C"},
      {x: "Fourth Quarter", value: ${offeringQuarter4}, fill:"#33CF88"}
    ];

    // set series data
    var chart_3 = anychart.column();
    chart_3.title("Offering(#)");
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

function centre() {
  stage.suspend();
  layer_1.zIndex(1000000);
  layer_2.zIndex(0);
  layer_3.zIndex(0);
  layer_4.zIndex(0);
  stage.resume();
};

function member() {
  stage.suspend();
  layer_1.zIndex(0);
  layer_2.zIndex(1000000);
  layer_3.zIndex(0);
  layer_4.zIndex(0);
  stage.resume();
};

function offering() {
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