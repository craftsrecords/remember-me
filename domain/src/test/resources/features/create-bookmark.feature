Feature: Create a bookmark

  As a curious but busy person,
  In order to remember to read this interesting article,
  I want to save the link for later

  Scenario: I want to create a new bookmark
    Given a link towards a useful resource
    When I bookmark it
    Then it is saved among my other bookmarks