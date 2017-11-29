angular.module('myApp.Controllers', []).controller('Controller1', function(Animal, $scope, $stateParams, $state){
	$scope.animals = Animal.get();
	$scope.deleteAnimal = function(id){
		Animal.delete({id:id}).$promise.then(
    //success
    function( value ){
    	$scope.animals= Animal.get();
    },
    //error
    function( error ){
    	alert(error);
    })}
		$scope.loadView2 = function(id){
			$state.go('view2', {
				id: id
			})}
		})
.controller('Controller2', function($scope, Animal, $stateParams){
	$scope.animal = Animal.get({id: $stateParams.id});
}).controller('Controller3', function($scope, $state, Animal, Owner, AnimalToOwner, $window){
	$scope.dupa = {};
	$scope.animal=new AnimalToOwner();
	$scope.owners = Owner.get();
	$scope.saveAnimal = function(id){
		$scope.animal.$save({id:id})
		.then(function(value){
			Animal.get();
			$state.go('view1');
			$('saveAnimalForm').submit();
		},
		function(error){
			 if (error.status == 409) {
                    $window.alert(error.data);
                  }})
	}})
.controller('Controller4', function($scope, Owner){
	$scope.owners = Owner.get();
	$scope.deleteOwner = function(id){
		Owner.delete({id:id}).$promise.then(
			function(value){
				$scope.owners = Owner.get();
			},
			function(error){
				alert(error);
			})}
}).controller('Controller5', function($scope, Owner, $window, $state){
	$scope.owner = new Owner();
	$scope.saveOwner = function(){
	if($scope.saveOwnerForm.$valid){
			$scope.owner.$save()
			.then(function(value){
				Owner.get();
				$state.go('view4');
				$('saveOwnerForm').submit();
				console.log('saving user'); // save $scope.user object
			},
			function(error){
				$window.alert("DUPA");
			})}
		else
		{
			console.log('Unable to save. Validation error!');
		}}});