Feature: Search bookmarks by tag

  Scenario: I want to get bookmarks which have a specific tag
    Given some bookmarks I saved
    And a theme I want to read about
    When I search for bookmarks about this theme
    Then I get bookmarks tagged with it