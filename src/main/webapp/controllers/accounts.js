app.controller('accounts',
    function($scope, $http, $log, responseService) {
        $scope.people = [];
        $scope.possibleRoles = ['ADMIN', 'USER'];

        $scope.loadPeople = function() {
            var httpRequest = $http.get('accounts')
                .then(function(response) {
                    $scope.people = response.data;
                }
            );
        };

        $scope.cancelAll = function() {
            $scope.editing = false;
            $scope.adding = false;
            $scope.errors = false;
            delete $scope.selectedRole;
            delete $scope.userparams;
        };

        $scope.edit = function(id) {
            $log.debug("edit user=" + id);

            var people = $scope.people;
            for (i = 0; i < people.length; i++) {
                $log.log("personid: " + people[i].id);
                if (people[i].id == id) {
                    $log.log("found personid: " + people[i].id);
                    $scope.userparams = {};
                    $scope.userparams.id = people[i].id;
                    $scope.userparams.fname = people[i].fname;
                    $scope.userparams.lname = people[i].lname;
                    $scope.userparams.email = people[i].email;
                    $scope.selectedRole = people[i].role;
                    $scope.editing = true;
                    break;
                }
            }
        };

        $scope.saveEdit = function() {
            $log.log("save edit user=" + id);
            $scope.editing = false;

            var id = $scope.userparams.id;
            var data = {
                fname: $scope.userparams.fname,
                lname: $scope.userparams.lname,
                email: $scope.userparams.email,
                role: $scope.selectedRole
            };

            $http.post('accounts/' + id, data).then(
                function() {
                    $scope.cancelAll()
                    $scope.loadPeople();
                }, function(response) {
                    responseService.processErrorResponse($scope, response);
                }
            );
        };

        $scope.remove = function(id) {
            $log.log("remove user=" + id);
            if (confirm("Are you sure you want to remove this user from the system?")) {
                $http.delete('accounts/' + id).then(function() {
                    $scope.cancelAll();
                    $scope.loadPeople();
                    console.info("User " + id + " properly deleted");
                }, function(response) {
                    console.error("Unable to delete User " + id);
                    responseService.processErrorResponse($scope, response);
                });
            }
        };

        $scope.add = function() {
            $log.log("add");
            $scope.adding = true;
        };

        $scope.saveAdd = function() {
            $log.log("save add [" + $scope.userparams.fname + ", " + $scope.userparams.lname + ", "
                + $scope.userparams.email + "," + $scope.selectedRole + "]");

            var data = {
                fname: $scope.userparams.fname,
                lname: $scope.userparams.lname,
                email: $scope.userparams.email,
                role: $scope.selectedRole
            };

            $http.put('accounts', data).then(
                function() {
                    $scope.cancelAll();
                    $scope.loadPeople();
                }, function(response) {
                    console.error("Unable to add User ");
                    responseService.processErrorResponse($scope, response);
                }
            );
        };

        $scope.resetPassword = function(id) {
            $log.log("reset password user=" + id);
            if (confirm("Are you sure you want to reset the password for this user?")) {
                $http.post("accounts/" + id + "/reset").then(
                    function() {
                        $scope.cancelAll();
                        $scope.loadPeople();
                    }, function(response) {
                        $scope.errors = true;
                        $scope.status = response.status
                        $scope.errorMessage = "Unable to reset password for user " + id + ": " + response.statusText;
                    }
                );
            }
        };

        $scope.setRole = function(role){
            $scope.selectedRole = role;
        };

        $scope.loadPeople();
    }
);