import { User } from ".";

export interface Message {
    id?: number;
    recipientId: number;
    content: string;
    dateAdded?: string;
    sender: User;
    read?: boolean;
}
  