import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { User } from '../../core';

@Component({
  selector: 'app-avatar-upload',
  templateUrl: './avatar-upload.component.html',
  styleUrls: ['./avatar-upload.component.css']
})
export class AvatarUploadComponent implements OnInit {

  @Input() user: User;
  @Output() avatarEmit = new EventEmitter<any>();

  myStyle = {
    'background-image': 'url(http://localhost:8080/assets/img/default_avatar.png)'
  }

  constructor() { }

  ngOnInit() {
    if (this.user){
      this.myStyle["background-image"] = 'url(' + this.user.avatar + ')'
    } else {
      this.user = {} as User;
    }
  }

  onFileChange(event) {
    let reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        console.log(reader.result)
        this.user.avatar = reader.result;
        this.myStyle["background-image"] = 'url(' + reader.result + ')';
        let eventEmit = {
          raw: reader.result.split(',')[1],
          base64: reader.result
        }
        this.avatarEmit.emit(eventEmit);
      }

    }
  }



}
