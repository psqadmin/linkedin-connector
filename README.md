### Mule LinkedIn Connector

The LinkedIn Connector will allow to connect to the [www.LinkedIn.com ](www.linkedIn.com)application.

### Installation and Usage #

For information about usage and installation you can check our documentation at http://mulesoft.github.com/linkedIn-connector.

### Technical Specification



- The LinkedIn Connector has been built using DevKit 3.4.0.
- It uses the linkedin-j-1.0.429 java wrapper for the LinkedIn APIs.
- It is compatible with MuleStudio version 3.5.0-201312091746.

### Reporting Issues #

We use GitHub:Issues for tracking issues with this connector. You can report new issues at this link https://github.com/mulesoft/linkedIn-connector/issues.

### Known Issues

Following APIs do not work as desired within LinkedIn Java wrapper (linkedinj-1.0.429) and are not included in the Mulesoft LinkedIn Connector

1. UpdateGroupMembership
2. GetPostComment
3. FlagPost
4. BookmarkJob

Following features are introduced by LinkedIn but not supported by the LinkedIn Java wrapper (linkedin-j-1.0.429) and are not included in the Mulesoft LinkedIn Connector

1. Reading company shares
2. Creating company shares
3. Targeting company shares
4. Commenting and liking company shares