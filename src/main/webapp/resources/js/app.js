angular
    .module('myApp', ['ngResource'])
    .service('TestCaseService', function ($log, $http) {
        return {
            getAll: function () {
               return  $http.get('resources/stub/test-cases.json')
            }
        }
    })
    .service('ExecService', function ($log, $http) {
            return {
                execTest: function (tcIdList) {
                   return  $http.get('/execTest?tcId=' + tcIdList)
                }
            }
        })
    .controller('TestCaseController', function ($scope, $log, TestCaseService,ExecService) {

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
               tcIdList += "," + selectedDefinitions[i].tcId;
            }
            ExecService.execTest(tcIdList);

            //$http.get('/execTest?')
            //console.log(selectedDefinitions);
        };
    });
