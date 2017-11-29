angular.module('myApp', [
	'myApp.Controllers',
	'ui.router', 
	'myApp.Services',
	'ngResource'
	]);

angular.module('myApp').config(function($stateProvider){
	$stateProvider.state('view1',{
		url:'/animal',
		controller:'Controller1',
		templateUrl:'partials/view1.html'
	}).state('view2',{
		url:'/animal/:id',
		controller: 'Controller2',
		templateUrl: 'partials/view2.html'
	}).state('view3',{
		url:'/animal/add/form',
		controller:'Controller3',
		templateUrl:'partials/view3.html'
	}).state('view4',{
		url:'/owner',
		controller:'Controller4',
		templateUrl:'partials/view4.html'
	}).state('view5',{
		url:'/owner/add/form',
		controller:'Controller5',
		templateUrl:'partials/view5.html'
	})})
	.run(function($state){
		$state.go('view1');
	});


/*angular.module('myApp').run(['state', function(state){
	$state.go('allAnimals');}]);*/






/*angular.module('myApp').controller('GetListController', function($scope, $http){
$http({
	method:'GET',
	url:'http://localhost:8080/animal/'
}).then(function successCallback(response) {
     $scope.animals = response.data;
  }, function errorCallback(response) {
  	 console.log("Cannot retrieve an animal's list");
  	})
});
angular.module('myApp').controller('GetController', function($scope, $http){
	$http({
	method:'GET',
	url:'http://localhost:8080/animal/'
}).then(function successCallback(response) {
     $scope.animals = response.data;
  }, function errorCallback(response) {
  	 console.log("Cannot retrieve an animal");
  	})
});*/
		