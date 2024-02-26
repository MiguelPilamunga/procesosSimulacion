import { Injectable } from '@angular/core';
import {Stomp}   from "@stomp/stompjs";
import SockJS from "sockjs-client";
import {BehaviorSubject} from "rxjs";
import {ProcesoDTO} from "../modelos/Proceso";

@Injectable({
  providedIn: 'root'
})
export class ProcesoService {

  //listener
  private procesoSubject: BehaviorSubject<ProcesoDTO[]> = new BehaviorSubject<ProcesoDTO[]>([]);

  // @ts-ignore

  private stompClient; // Specify Stomp.Client type

  constructor() {
    this.initializeWebSocketConnection();
  }
  initializeWebSocketConnection() {
    const serverUrl = '//172.17.0.2:3000/chat-socket'
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
  }

  joinRoom(roomID: string): void {
    this.stompClient.connect({}, () => {
      this.stompClient.subscribe(`/topic/${roomID}`, (message: any) => {
        const messageBody = JSON.parse(message.body);
        const procesoList = messageBody.body; // Access the nested list of ProcesoDTO objects
        this.procesoSubject.next(procesoList);
      });
    });
  }


  sendMessage(roomID: string, numProcesos: number) {
    this.stompClient.send(`/app/chat/${roomID}`, {}, JSON.stringify({ numeroProcesos: numProcesos }));
  }

  getProcesoSubject() {
    return this.procesoSubject.asObservable();
  }

}
