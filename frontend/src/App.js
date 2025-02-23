import React, { useEffect, useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [data, setData] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/")
      .then((response) => {
        setData(response.data);
        console.log("data" + response.data);
      })
      .catch((error) => {
        console.error("There was an error fetching the data!", error);
      });
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <h1 className="text-3xl font-bold underline">Data from Backend:</h1>
        <p className="mt-4 text-lg">{data}</p>
      </header>
    </div>
  );
}

export default App;
