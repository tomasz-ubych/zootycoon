angular.module('myApp.Services', []);

angular.module('myApp.Services').service('Animal', function($resource){
	 return $resource('http://localhost:8080/animal/:id', { id: '@_id' }, {
    update: {
      method: 'PUT' // this method issues a PUT request
    }
  });
});
angular.module('myApp.Services').service('Owner', function($resource){
	 return $resource('http://localhost:8080/owner/:id', { id: '@_id' }, {
    update: {
      method: 'PUT' // this method issues a PUT request
    }
});
  });
angular.module('myApp.Services').service('AnimalToOwner', function($resource){
	 return $resource('http://localhost:8080/owner/:id/animal', { id: '@_id' }, {
    update: 
    {
      method: 'PUT' // this method issues a PUT request
    	}
	,save:
	{
		 method: 'POST',
         headers: { 'Content-Type': 'application/json' }
	}
	})}
);
angular.module('myApp.Services').service('Owner', function($resource){
	 return $resource('http://localhost:8080/owner/:id', { id: '@_id' }, {
    update: {
      method: 'PUT' // this method issues a PUT request
    }
  });
});

