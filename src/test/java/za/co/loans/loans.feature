Feature: Loans generator
    Background:
        * configure ssl = true
    Scenario: Validate loan application for invalid idNumber

        Given url 'http://localhost:8080'
        And path '/loans'
        And request { "bankAccount":{	"accountNumber": 1234567890,"bankName": "SCRUM_BANK"},"idNumber": 2201025391084,"name": "Pontsho","surname": "Molewa"}
        And header Content-Type = 'application/json'
        When method POST
        Then match $ == {reference:'#notnull', validationStatus:false, errors:'#notnull', warnings:'#notnull'}
        And print response

    Scenario: Validate loan application for invalid Name

        Given url 'http://localhost:8080'
        And path '/loans'
        And request { "bankAccount":{	"accountNumber": 1234567890,"bankName": "SCRUM_BANK"},"idNumber": 9901025391084,"name": "Donald1","surname": "Duck"}
        And header Content-Type = 'application/json'
        When method POST
        Then match $ == {reference:'#notnull', validationStatus:false, errors:'#notnull', warnings:'#notnull'}
        And print response

    Scenario: Validate loan application for invalid Surname

        Given url 'http://localhost:8080'
        And path '/loans'
        And request { "bankAccount":{	"accountNumber": 1234567890,"bankName": "SCRUM_BANK"},"idNumber": 9901025391084,"name": "Donald","surname": "Duck1"}
        And header Content-Type = 'application/json'
        When method POST
        Then match $ == {reference:'#notnull', validationStatus:false, errors:'#notnull', warnings:'#notnull'}
        And print response

    Scenario: Validate loan application for invalid bank name

        Given url 'http://localhost:8080'
        And path '/loans'
        And request { "bankAccount":{	"accountNumber": 1234567890,"bankName": "MOLEWA_BANK"},"idNumber": 9901025391084,"name": "Pontsho","surname": "Molewa"}
        And header Content-Type = 'application/json'
        When method POST
        Then match $ == {reference:'#notnull', validationStatus:true, errors:'#notnull', warnings:'#notnull'}
        And print response

    Scenario: Validate loan application for invalid Account Number

        Given url 'http://localhost:8080'
        And path '/loans'
        And request { "bankAccount":{	"accountNumber": 123456789,"bankName": "MOLEWA_BANK"},"idNumber": 9901025391084,"name": "Pontsho","surname": "Molewa"}
        And header Content-Type = 'application/json'
        When method POST
        Then match $ == {reference:'#notnull', validationStatus:false, errors:'#notnull', warnings:'#notnull'}
        And print response