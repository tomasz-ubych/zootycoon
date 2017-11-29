angular.module('demo', [])
.controller('Hello', function($scope, $http){
	$http.get('http://localhost:8088/animal').

		then(function(response) {
            
	$scope.content= response.data;        
	});

});
