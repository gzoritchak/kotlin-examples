var kotlinApp = angular.module("kotlinApp", []);

kotlinApp.controller("kotlinCtrl", function kotlinCtrl($scope, service){

    $scope.ageMax = 120;
    $scope.humans = [];

    $scope.getHumans = function (){
        service.getHumans($scope.ageMax)
            .then(function(data){
                $scope.humans = data.data;
            });
    };

});

kotlinApp.factory('service', function ($http) {
    return {
        getHumans: function (ageMax){
            return $http.get('/humans', {params: { ageMax: ageMax }})
                .success(function (result) {
                    return result.data;
                });
        }
    }
});




