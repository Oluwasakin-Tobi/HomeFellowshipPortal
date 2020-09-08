
    var appname = angular.module('myModule', []);
    appname.controller('appCtrl', ['$scope',
        function($scope) {
            $scope.greeting = {text: 'Hello'};
            $scope.divshow = false;
            $scope.ShowHide = function() {
                //If DIV is visible it will be hidden and vice versa.
                $scope.divshow = $scope.showdiv;
            };
        }

    ]);
    appname.controller('partydetailsCtrl', ['$scope',
        function($scope) {
            $scope.partyDetails =
                    [{ 
                            'name': '',
                            'address': '',
                            'city': '',
                            'country': '',
                            'partyIDType': '',
                            'partyID': ''
                        }];
                    
            $scope.addNew = function(partyDetails) {
              //  console.log('+++ In Party Details Now.. +++ ' + partyDetails);
//                $scope.partyDetails.push({
//                    'pname': partyDetails.pname,
//                    'paddress': partyDetails.paddress,
//                    'pcity': partyDetails.pcity,
//                    'pcountry': partyDetails.pcountry,
//                    'partyIDType': partyDetails.partyIDType,
//                    'partyID': partyDetails.partyID
//                });
                $scope.partyDetails.push({ 
                    'name': '',
                    'address': '',
                    'city': '',
                    'country': '',
                    'partyIDType': '',
                    'partyID': ''
                });
                $scope.PD = {};
                 
            };
            $scope.remove = function() {
                var newDataList = [];
                $scope.selectedAll = false;
                angular.forEach($scope.partyDetails, function(selected) {
                    if (!selected.selected) {
                        newDataList.push(selected);
                    }
                });
                $scope.partyDetails = newDataList;
            };

            $scope.checkAll = function() {
                if (!$scope.selectedAll) {
                    $scope.selectedAll = true;
                } else {
                    $scope.selectedAll = false;
                }
                angular.forEach($scope.partyDetails, function(partyDetails) {
                    partyDetails.selected = $scope.selectedAll;
                });
            };
            
            $scope.preSubmit = function(){       
                
                var json = angular.toJson($scope.partyDetails);
               // console.log('++++ json is '+json+ ' ++++');
               // console.log('++++ pd is '+$scope.partyDetails.toString()+ ' ++++')       
               // console.log(JSON.parse((angular.toJson($scope.partyDetails))));
                $scope.partydetailListToSubmit=angular.toJson($scope.partyDetails); 
            };

        }

    ]);

    $(document).ready(function() {

        $('#example').DataTable();
        $('.accordion-toggle').on('click', function(event) {
            event.preventDefault();
            // create accordion variables
            var accordion = $(this);
            var accordionContent = accordion.next('.accordion-content');
            var accordionToggleIcon = $(this).children('.toggle-icon');

            // toggle accordion link open class
            accordion.toggleClass("open");
            // toggle accordion content
            accordionContent.slideToggle(250);

            // change plus/minus icon
            if (accordion.hasClass("open")) {
                accordionToggleIcon.html("<i class='fa fa-minus'></i>");
            } else {
                accordionToggleIcon.html("<i class='fa fa-plus'></i>");
            }
        });
//        $(".amt").bigText({
//            rotateText: null, // Rotates the text by X degrees
//            fontSizeFactor: 0.7, // used to give some vertical spacing for letters that overflow the line-height 
//            maximumFontSize: null, // limits the maximum font size of your text
//            limitingDimension: "both", // In which dimension the font size should be limited. Possible values: "width", "height" or "both". 
//            horizontalAlign: "left",
//            verticalAlign: "center",
//            textAlign: "left"
//        });
        $('[data-submenu]').submenupicker();
    });
