# Connection to MS QNA

## Goal

The dialog with a user is transferred to the trained AI bot https://qnamaker.ai that uses FAQ for training and answers users in a native language. 

```
(!) Use case: self assistance page
The user is prompted into a chat with the bot to solve a problem
```

## Usage

To call the plugin use the link of this kind: ```http://plugins.miniapps.run/ai_msqna?....``` 

For instance:
```<page version="2.0">
  <div>
    Thank you for using the service!
  </div>
 
  <navigation>
    <link pageId="http://plugins.miniapps.run/ai_msqna?query=Hello&amp;back_url=index.xml&amp;token=23143434r54:er4hhigdsfsdafdadf">
      Back to home page
    </link>
  </navigation>
</page>
```

## Parameters
|Parameter    |Mandatory    |Description          |
|-------------|-------------|---------------------|
|query        |Yes          |The text of the request to be passed to the bot https://qnamaker.ai when the user follows the link. For instance, "Hello!" causes the bot to welcome the user. As another option, it can be a question the bot is trained to answer.|
|back_url     |Yes          |The address of the page to land the user back to the dialog. The Return button is always accessible by the user while chatting with the bot.|
|token        |No           |Developer's token https://qnamaker.ai of the bot. It is not required, if the parameter ms-qna.token is indicated in the service configuration.|

