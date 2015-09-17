package com.github.jlgrock.informatix.workmanager.controllers
import com.github.jlgrock.informatix.workmanager.domain.UserAccount
import com.github.jlgrock.informatix.workmanager.domain.UserAccountDTO
import com.github.jlgrock.informatix.workmanager.exceptions.UserException
import com.github.jlgrock.informatix.workmanager.services.accounts.AccountsServiceFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.validation.Valid
/**
 *
 */
@RestController
@RequestMapping("/accounts")
class AccountsController extends AbstractSpringController {

    @Autowired
    AccountsServiceFactory accountsServiceFactory

    private static Logger LOGGER = LoggerFactory.getLogger(AccountsController.class);

    @RequestMapping(method = RequestMethod.GET)
    public Collection<UserAccount> get() {
        accountsServiceFactory.getAccountsService().getAll()
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public UserAccount get(@PathVariable("id") int id) {
        if (id == null) {
            throw new UserException("id is null. Cannot continue with edit")
        }
        accountsServiceFactory.getAccountsService().get(id)
    }

    @RequestMapping(method = RequestMethod.POST, value="/{id}")
    public UserAccount post(
            @PathVariable("id")int id,
            @RequestBody @Valid UserAccountDTO userAccountData) {

        if (id == null) {
            throw new UserException("id is null. Cannot continue with edit")
        }

        accountsServiceFactory.getAccountsService().update(id, userAccountData)
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UserAccount put(
            @RequestBody @Valid UserAccountDTO userAccountData) {
        LOGGER.debug("userAccountData: ${userAccountData}")
        if (userAccountData == null) {
            throw new UserException("userAccountData is null. Cannot continue with add")
        }
        accountsServiceFactory.getAccountsService().add(userAccountData)
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public UserAccount delete(@PathVariable("id") int id) {
        if (id == null) {
            throw new UserException("id is null. Cannot continue with delete")
        }
        accountsServiceFactory.getAccountsService().delete(id)
    }

    @RequestMapping(method = RequestMethod.GET, value="/reset")
    public UserAccount resetPassword(@RequestParam("email") String email) {
        if (email == null) {
            throw new UserException("id is null. Cannot continue with password reset")
        }

        accountsServiceFactory.getAccountsService().resetPassword(email)
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}/validate")
    public UserAccount validateUser(@PathVariable("id")int id, @RequestParam token) {
        if (id == null) {
            throw new UserException("id is null. Cannot continue with edit")
        }
        if (token == null) {
            throw new UserException("id is null. Cannot continue with edit")
        }

        accountsServiceFactory.getAccountsService().askForVerification(id)
    }

}
