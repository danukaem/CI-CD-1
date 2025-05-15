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

  constructor(private http: HttpClient) {
    this.getUsers();
  }

  editUser(user: { id: number; name: string; age: number }) {
    const updatedUser = { ...user, name: 'Updated Name' };
    this.http
      .put(`https://akanita.online/user/${user.id}`, updatedUser)
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
    this.http.delete(`https://akanita.online/user/${user.id}`).subscribe(
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
    this.http.post('https://akanita.online/user', newUser).subscribe(
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
    this.http.get<any>('https://akanita.online/user').subscribe(
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
