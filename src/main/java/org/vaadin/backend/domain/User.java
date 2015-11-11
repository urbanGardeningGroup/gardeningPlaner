import java.util.Date;

 public class User
    {
        protected String _name;
        protected String _firstname;
        protected String _nickname;
        protected String _email;
        protected String _password;
        protected Boolean  _isValidated;
        protected Date _accountDeletedOn;

        public void setName(String name)
        {
            _name = name;
        }
        public String getName()
        {
            return _name;
        }
        
        public void setFirstname(String firstname)
        {
            _firstname = firstname;
        }
        public String getFirstname()
        {
            return _firstname;
        }

        public void setNickname(String nickname)
        {
            _nickname = nickname;
        }
        public String getNickname()
        {
            return _nickname;
        }

        public void setEmail(String email)
        {
            _email = email;
        }
        public String getEmail()
        {
            return _email;
        }

        public void setPassword(String password)
        {
            _password = password;
        }
        public String getPassword()
        {
            return _password;
        }

        public Boolean isValidated()
        {
            return _isValidated;
        }

        public Boolean isDeleted()
        {
            return false;
        }
    }
