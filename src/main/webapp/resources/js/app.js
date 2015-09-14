angular
    .module('myApp', ['ngResource'])
    .service('TestCaseService', function ($log, $http) {
        return {
            getAll: function () {
               return  $http.get('resources/stub/test-cases.json')
            }
        }
    }).service('TestBedService', function ($log, $http) {
              return {
                  getTestBed: function () {
                     return  $http.get('resources/stub/test-bed.json')
                  }
              }
    }).service('ExecService', function ($log, $http) {
            return {
                execTest: function (tcIdList, testBed) {
                   return  $http.get('execTest?tcId=' + tcIdList + '&testBed=' + testBed)
                }
            }
        }).controller('TestCaseController', function ($scope, $log, TestCaseService,ExecService, TestBedService) {

        TestBedService.getTestBed().then(function(res){
            $scope.testBeds =  res.data;
        });

         TestCaseService.getAll().then(function(res){
                    $scope.testCases =  res.data;
                });

        $scope.submitTestCases = function() {
            var selectedDefinitions = $scope.testCases.filter(function(def) {

              return def.checked;
            });
            console.log(selectedDefinitions);
            var tcIdList = "";
            for(var i = 0; i<selectedDefinitions.length;i++){
               tcIdList +=  selectedDefinitions[i].tcId +",";
            }
            var testBed = $scope.selectedTestBed.id;
            console.log($scope.selectedTestBed.id);
            ExecService.execTest(tcIdList, testBed);

            //$http.get('/execTest?')
            //console.log(selectedDefinitions);
        };

        $scope.checkAll = function () {
        if ($scope.selectedAll) {
            $scope.selectedAll = true;
        } else {
            $scope.selectedAll = false;
        }
        angular.forEach($scope.testCases, function (item) {
            item.checked = $scope.selectedAll;
        });

    };
    });
