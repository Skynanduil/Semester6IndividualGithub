import './App.css';
import SendMessageToBroker from "./modules/SendMessageToBroker";
import GetStaticResponse from "./modules/GetStaticResponse";
import CreateMessage from "./modules/CreateMessage";
import GetMessage from "./modules/GetMessage";
import EditMessage from "./modules/EditMessage";
import DeleteMessage from "./modules/DeleteMessage";
import FAAS from "./modules/FAAS";
import LoginButton from './modules/LoginButton';
import LogoutButton from './modules/LogoutButton';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Welcome to the Walking Skeleton</h1>
          <div>
              <h2>Gateway</h2>
              <GetStaticResponse/>
              <hr/></div>
          <div>
              <h2>Messaging</h2>
              <SendMessageToBroker/>
              <hr/>
          </div>
          <div>
              <h2>Database</h2>
              <h4>create</h4>
              <CreateMessage/>
              <h4>get</h4>
              <GetMessage/>
              <h4>edit</h4>
              <EditMessage/>
              <h4>delete</h4>
              <DeleteMessage/>
              <hr/>
          </div>
          <div>
            <h2>FAAS</h2>
            <FAAS/>
          </div>
          <div>
            <h2>Auth0</h2>
            <LoginButton/>
            <LogoutButton/>
          </div>

      </header>
    </div>
  );
}

export default App;