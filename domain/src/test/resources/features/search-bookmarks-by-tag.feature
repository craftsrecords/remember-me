Feature: Search bookmarks by tag

  Scenario: I want to get bookmarks which have some specific tags
    Given some bookmarks I saved
    And some themes I want to read about
    When I search for bookmarks about these themes
    Then I get bookmarks tagged with these themes