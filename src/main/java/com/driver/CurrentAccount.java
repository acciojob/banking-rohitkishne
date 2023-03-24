package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    private int mfc[] = new int[26];
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000.0);
        this.tradeLicenseId = tradeLicenseId;
        if(getMinBalance()<5000.0)
        {
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        int n = tradeLicenseId.length();
        if(!isValidId(tradeLicenseId))
        {
            int mfch = getMfc(tradeLicenseId);

            if(n%2 == 0)
            {
                if(mfch >= (n/2+1)) {
                    throw new Exception("Valid License can not be generated");
                }
            }
            else
            {
                if(mfch >= (n/2+2))
                {
                    throw new Exception("Valid License can not be generated");
                }
            }
            char maxFreqChar = getMaxfc(mfch);
            char[] ans = new char[n];
            int idx = 0;
            for(idx = 0; idx<n; idx+=2)
            {
                if(mfc[maxFreqChar-'A']>0)
                {
                    ans[idx] = maxFreqChar;
                    mfc[maxFreqChar-'A']--;
                }
                else
                {
                    break;
                }

            }

            for(int i=0; i<26; i++)
            {
                if(idx>=n)
                {
                    idx = 1;
                }

                while(mfc[i]>0)
                {
                    ans[idx] = (char)('A'+i);
                    idx+=2;
                }
            }

            String str = "";
            for(int i=0; i<n; i++)
            {
                str = str + ans[i];
            }

            tradeLicenseId = str;
        }

    }

    public Character getMaxfc(int max){
        char ans = '0';
        int maxFreq = max;
        for(int i=0; i<26; i++)
        {
            if(mfc[i] == max)
            {
                ans = (char)('A' + i);
                break;
            }
        }
        return ans;
    }
    public int getMfc(String id)
    {
        int n = id.length();
        for(int i=0; i<n; i++)
        {
            char ch = id.charAt(i);
            mfc[ch-'A']++;
        }
        int max = -1;
        for(int i=0; i<26; i++)
        {
            if(mfc[i]>max)
            {
                max = mfc[i];
            }
        }
        return max;
    }

    public boolean isValidId(String id)
    {
        int n = id.length();
        for(int i=0; i<n-1; i++)
        {
            char ch1 = id.charAt(i);
            char ch2 = id.charAt(i+1);
            if(ch1 == ch2)
                return false;
        }
        return true;
    }


}
