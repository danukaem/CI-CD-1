import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'demoFrontend';
  users: { id: number; name: string; age: number }[] = [];

  userName: string = '';
  userAge: number = 0;
  url ="https://akanita.online/";
  // url ="https://3.90.41.113/";

  constructor(private http: HttpClient) {
    this.getUsers();
  }

  editUser(user: { id: number; name: string; age: number }) {
    const updatedUser = { ...user, name: 'Updated Name' };
    this.http
      .put(`${this.url}user/${user.id}`, updatedUser)
      .subscribe(
        (response) => {
          console.log('User updated successfully:', response);
          this.getUsers();
        },
        (error) => {
          console.error('Error updating user:', error);
        }
      );
  }

  deleteUser(user: { id: number; name: string; age: number }) {
    this.http.delete(`${this.url}user/${user.id}`).subscribe(
      (response) => {
        console.log('User deleted successfully:', response);
        this.getUsers();
      },
      (error) => {
        console.error('Error deleting user:', error);
      }
    );
  }
  addUser(name: any, age: any) {
    const newUser = { name: name, age: age };
    this.http.post(`${this.url}user`, newUser).subscribe(
      (response) => {
        console.log('User added successfully:', response);
        // Add the new user to the local list
        this.getUsers();
      },
      (error) => {
        console.error('Error adding user:', error);
      }
    );
  }

  getUsers() {
    this.http.get<any>(`${this.url}user`).subscribe(
      (data) => {
        console.log('Fetched users:', data);

        this.users = data['data'];
      },
      (error) => {
        console.error('Error fetching users:', error);
      }
    );
  }
}
