app.factory('securityService', function($rootScope, $http) {

    return {
        authenticate : function(credentials, callback) {

            var postheaders = credentials ? {
                authorization : "Basic "
                + btoa(credentials.email + ":"
                    + credentials.password)
            } : {};

            $http.get('principal', {
                headers : postheaders
            }).then(function(response) {
                if (response.data['name']) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = true; //TODO fix
                    //$rootScope.authenticated = false;
                }
                callback && callback($rootScope.authenticated);
            }, function() {
                $rootScope.authenticated = false;
                callback && callback(false);
            });

        }
    };

});
