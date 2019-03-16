Feature: Create a bookmark

  As a curious but busy person,
  In order to remember to read this interesting article,
  I want to save the link for later

  Scenario: I want to create a new bookmark
    Given a link towards a useful resource
    And a name describing the resource
    And some tags classifying the resource
    When I bookmark it
    Then it is saved among my other bookmarks

  Scenario: I want to create a bookmark I already have
    Given a link that I have bookmarked
    When I bookmark it
    Then I am notified that the bookmark already exists