# Complexity Analysis

| Operation | Data Structure | Time Complexity |
|------------|---------------|-----------------|
| Insert Character | Stack | O(1) |
| Backspace | Stack | O(1) |
| Delete | Stack | O(1) |
| Move Cursor Left | Stack | O(1) |
| Move Cursor Right | Stack | O(1) |
| Undo | Stack | O(1) |
| Redo | Stack | O(1) |
| Search (KMP) | KMP Algorithm | O(n + m) |
| Display Text | Two Stacks | O(n) |

Where:

- n = length of the document
- m = length of the search pattern

The editor uses two stacks to maintain characters on either side of the cursor. Stack operations such as push and pop execute in constant time, allowing insertion, deletion and cursor movement to remain efficient regardless of document size.

Undo and redo functionality are implemented using action history stacks. Since actions are pushed and popped from the top of the stack, both operations execute in O(1) time.

Text searching is implemented using the Knuth-Morris-Pratt (KMP) algorithm. Unlike naive string matching, KMP avoids rechecking previously matched characters and achieves O(n + m) time complexity.

Displaying text requires reconstructing the document from the contents of the left and right stacks, resulting in O(n) complexity.