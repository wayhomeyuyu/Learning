function checkFileSize(fileID, sizeLimit){
  file_input = document.getElementById(fileID);
	if (!file_input) {
        alert("Um, couldn't find the fileinput element.");
        return true;
    } else if (!file_input.files) {
        alert("This browser doesn't seem to support the `files` property of file inputs.");
        return true;
    } else if (!file_input.files[0]) {
        alert("Please select a file before clicking 'Load'");
        return true;
    } else {
        file = file_input.files[0];
        if (file.size > sizeLimit) {
        	alert("File " + file.name + " is larger than " + Math.round(sizeLimit/1024/1024) + " MB.");
        	return false;
        } else {
        	return true;
        }
    }
}